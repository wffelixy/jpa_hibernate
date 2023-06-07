package br.com.crudhibernate;

import br.com.crudhibernate.view.ClienteForm;

public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ClienteForm clienteForm = new ClienteForm();
                clienteForm.setVisible(true);
            }
        });
    }
}
