package br.com.hospede.model.modeloTabela;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableCellRenderer;


public class JTableRenderer extends DefaultTableCellRenderer{
    
     protected void setValue(Object value){
        if(value instanceof ImageIcon){
            if(value != null){
                ImageIcon imageIcon = (ImageIcon) value;
                setIcon(imageIcon);
                
            } else {
                setText("");
                setIcon(null);
            }
        } else {
            super.setValue(value);
        }
    }
}
