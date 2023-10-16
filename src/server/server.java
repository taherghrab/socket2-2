package server;

import Model.operation;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // TODO Auto-generated method stub
        // Réservation du port et acceptation de connexion
        ServerSocket ss = new ServerSocket(1237);

        System.out.println("Le server attend la connexion un client ");
        Socket s = ss.accept();
        System.out.println("client connécté");
        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(os);
        ObjectInputStream in = new ObjectInputStream(is);
        operation op =  (operation)  in.readObject();// Conversion du type Object vers Operation

        int nb1 = op.getOp1();
        int nb2 = op.getOp2();
        int ops =  op.getO();

        int res = 0;

            /*InputStreamReader isr= new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
         int   g = Integer.parseInt(br.readLine());
            int z=Integer.parseInt(br.readLine());
            int T = Integer.parseInt(br.readLine());
            int res;*/
        switch (ops){
            case 1 : res = nb1*nb2; break;
            case 2:  res=nb1-nb2;break;
            case 3:res=nb1/nb2;break;
            case 4: res=nb1+nb2;break;
            default:
                throw new IllegalStateException("Unexpected value: " + ops);
        }
           /* PrintWriter pw = new PrintWriter(os, true);
            pw.println(res);*/
        //System.out.println(res);*/
        op.setRes(res);// Le renvoi du même objet vers le client après modification de la propriétés 'Res'
        out.writeObject(op);
        s.close();
        ss.close();


    }
}
