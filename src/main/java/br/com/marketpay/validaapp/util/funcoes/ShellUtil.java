package br.com.marketpay.validaapp.util.funcoes;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

public class ShellUtil {
	public String executeCommand(String command) throws IOException {
		ArrayList<String> commands = new ArrayList<String>();
		commands.add("/bin/bash");
		commands.add("-c");
		commands.add(command);

		StringBuilder builder = new StringBuilder();
		BufferedReader br = null;

		try {
			ProcessBuilder p = new ProcessBuilder(commands);
			Process process = p.start();
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			br = new BufferedReader(isr);

			String line;
			while ((line = br.readLine()) != null) {
				builder.append(line.trim() + "\n");
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
			throw ioe;
		} finally {
			secureClose(br);
		}
		return builder.toString().trim();
	}
	
	public List<String> executeCommandWithKey(String server, String usuario, File key, String command) throws IOException{
		Connection conn = new Connection(server);
		conn.connect();
		boolean isAuth = conn.authenticateWithPublicKey(usuario, key, "");

		if (isAuth == false) {
			throw new IOException("Authentication failed.");
		}

		Session sess = conn.openSession();
		sess.execCommand(command);
		InputStream inp = sess.getStdout();
		InputStreamReader reader = new InputStreamReader(inp);
		BufferedReader br = new BufferedReader(reader);
		
		List<String> results = new ArrayList<String>();

		String line;
		while ((line = br.readLine()) != null) {
			results.add(line.trim());
		}
		
		sess.close();
		conn.close();

		return results;
	}
	    
	 public String executeScript(String pathScript) {
		StringBuilder builder = new StringBuilder();
        try {
            Process proc = Runtime.getRuntime().exec("sh " + pathScript);
            BufferedReader read = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            try {
                proc.waitFor();
            } catch (InterruptedException e) {
            	e.printStackTrace();
            }
            while (read.ready()) {
            	builder.append(read.readLine() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }
	 
	public String getInfoMemoriaTotal() throws IOException{
		String memoria = executeCommand("cat /proc/meminfo | grep \\MemTotal | cut -d\\: -f2-").trim();
		BigDecimal total = new BigDecimal(memoria.trim().split(" ")[0].trim()).divide(new BigDecimal(1024000));
		return String.valueOf(getNF2Decimais(total.doubleValue())) + " GB";
	}
	
	public String getInfoMemoriaUsada() throws IOException{
		String memoria = executeCommand("free | grep \"\\-/+ buffers/cache\" | cut -d\\: -f2-");
		BigDecimal total = new BigDecimal(memoria.trim().split(" ")[0].trim()).divide(new BigDecimal(1024000));
		return String.valueOf(getNF2Decimais(total.doubleValue())) + " GB";
	}
	
	public String getInfoMemoriaLivre() throws IOException{
		String memoria = executeCommand("free | grep \"\\-/+ buffers/cache\" | cut -d\\: -f2-");
		BigDecimal total = new BigDecimal(memoria.trim().split("    ")[1].trim()).divide(new BigDecimal(1024000));
		return String.valueOf(getNF2Decimais(total.doubleValue())) + " GB";
	}
	
//	public static void main(String[] args) throws IOException {
//		String memoria = new ShellUtil().executeCommand("free | grep Mem: | cut -d\\: -f2-");
//		BigDecimal total = new BigDecimal(memoria.trim().split("    ")[2].trim()).divide(new BigDecimal(1024000));
//		System.out.println(String.valueOf(new ShellUtil().getNF2Decimais(total.doubleValue())) + " GB");
//	}
	
	public String getNF2Decimais(double number){
		NumberFormat nf = NumberFormat.getInstance(Locale.ITALY);
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		return nf.format(number);
	}
	
    private void secureClose(Closeable resource) {
        try {
            if (resource != null) {
                resource.close();
            }
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
    }
}