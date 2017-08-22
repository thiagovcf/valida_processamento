package br.com.marketpay.validaapp.web.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flash{

     private String msg;

     private FlashTipo tipo;

     public enum FlashTipo {

          danger, info, warning, success;

     }

     public String toString() {

          return getMsg();
     }

}