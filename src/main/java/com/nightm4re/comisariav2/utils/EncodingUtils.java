/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nightm4re.comisariav2.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author Nightm4re
 */
public class EncodingUtils {

    static void encode(File imagen, File archivodestino) {

        try {
            byte[] buffer = new byte[(int) imagen.length() + 100];
            int length = new FileInputStream(imagen).read(buffer);
            String encodedimage = Base64.getEncoder().encodeToString(buffer);

            try {
                new File("./database/" + ".imgfiles/").mkdirs();
                BufferedWriter writer = new BufferedWriter(new FileWriter(archivodestino));
                writer.write(encodedimage);

                writer.close();
            } catch (IOException e) {
                Utils.LogToFile(e);
            }

        } catch (IOException e) {
            Utils.LogToFile(e);
        }

    }

    static BufferedImage decode(File codedimage) {

        String base64 = "";
        BufferedImage image = null;
        byte[] imageByte;
        try {           
                new File("./database/" + ".imgfiles/").mkdirs();
                Scanner sc = new Scanner(codedimage);
                while (sc.hasNextLine()) {
                    base64 = sc.nextLine();
                }
                imageByte = Base64.getDecoder().decode(base64);
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                image = ImageIO.read(bis);
                    bis.close();

        } catch (IOException e) {
            Utils.LogToFile(e);
        }

        return image;

    }

}
