/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package armor;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import sun.security.util.Length;

/**
 *
 * @author HUYVU
 */
public class ArmorServer extends UnicastRemoteObject
        implements ArmorInterface {

    List<ArmorDTO> listArmor = new Vector<ArmorDTO>();
    //private final String fileName = "ArmorData.txt";

    //List<ArmorDTO> armorList = new ArrayList<>();
    String fileName;

    public ArmorServer(String fileName) throws RemoteException {
        super();
        this.fileName = fileName;
    }

    public List<ArmorDTO> getData(String fileName) throws RemoteException {
        //Vector data = new Vector(0);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); //create format date
        String line;
        try {

            FileReader f = new FileReader(fileName);
            BufferedReader br = new BufferedReader(f);
            //Date date = new Date();
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.length() > 0) {
                    StringTokenizer stk = new StringTokenizer(line, "`");
                    String armorId = stk.nextToken();
                    String classification = stk.nextToken();
                    String description = stk.nextToken();
                    String status = stk.nextToken();
                    Date data = format.parse(stk.nextToken());
                    int defence = Integer.parseInt(stk.nextToken());
                    ArmorDTO armor = new ArmorDTO(armorId, classification, description, status, data, defence);
                    listArmor.add(armor);
                }
            }
            br.close();
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listArmor;
    }

    public boolean writeFile(String fileName) throws RemoteException {
        try {
            FileWriter f = new FileWriter(fileName);
            PrintWriter pw = new PrintWriter(f);
            try {
                pw = new PrintWriter(fileName);
                for (ArmorDTO armorDTO : listArmor) {
                    pw.println(armorDTO.toString());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            pw.close();
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean createArmor(ArmorDTO dto) throws RemoteException {
        if (findByArmorID(dto.getArmorID()) != null) {
            return false;
        } else {
            try {
                FileWriter f = new FileWriter(fileName);
                PrintWriter pw = new PrintWriter(f);
                try {
                    pw = new PrintWriter(fileName);
                    listArmor.add(dto);
                    for (ArmorDTO armorDTO : listArmor) {
                        pw.println(armorDTO.toString());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
                pw.close();
                f.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public ArmorDTO findByArmorID(String id) throws RemoteException {
        findAllArmor();
        for (ArmorDTO findArmor : listArmor) {
            if (findArmor.getArmorID().equals(id)) {
                return findArmor;
            }
        }
        return null;
    }

    @Override
    public List<ArmorDTO> findAllArmor() throws RemoteException {
        if (listArmor != null) {
            listArmor.removeAll(listArmor);
        }

        listArmor = getData(fileName);
        return listArmor;
    }

    @Override
    public boolean removeArmor(String id) throws RemoteException {
        if (listArmor != null) {
            listArmor.removeAll(listArmor);
        }
        // ArmorDTO dto = new ArmorDTO();
        ArmorDTO findArmor = findByArmorID(id);
        listArmor.remove(findArmor);
        return writeFile(fileName);
    }

    @Override
    public boolean updateArmor(ArmorDTO dto) throws RemoteException {
        ArmorDTO findArmor = findByArmorID(dto.getArmorID());
        listArmor.remove(findArmor);
        listArmor.add(dto);
        return writeFile(fileName);
    }
}
