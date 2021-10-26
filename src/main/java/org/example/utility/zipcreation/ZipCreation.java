package org.example.utility.zipcreation;

import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipCreation {

    String userdir=System.getProperty("user.dir");
    String path=userdir+"\\src\\main\\java\\org\\example\\resources\\zipfiles\\files\\singlefiles\\CommonExcelWindowsShortcuts-01.pdf";
    String zipname;

    @Test
    public void zipsinglefiles() throws IOException {
        File file=new File(path);

        zipname=userdir+"\\src\\main\\java\\org\\example\\resources\\zipfiles\\zippedfiles\\"+file.getName().concat(".zip");

        FileOutputStream fos=new FileOutputStream(zipname);
        ZipOutputStream zos=new ZipOutputStream(fos);

        zos.putNextEntry(new ZipEntry(file.getName()));
        byte[] bytes= Files.readAllBytes(Paths.get(path));
        zos.write(bytes,0,bytes.length);
        zos.closeEntry();
        zos.close();
    }
@Test
    public void zipmultiplefiles() throws IOException {

        String multiplefilespath=userdir+"\\src\\main\\java\\org\\example\\resources\\zipfiles\\files\\multiplefiles";
        File multifile=new File(multiplefilespath);
        String[] contents=multifile.list();
    zipname=userdir+"\\src\\main\\java\\org\\example\\resources\\zipfiles\\zippedfiles\\"+"multifile"+".zip";
        FileOutputStream fos=new FileOutputStream(zipname);
        ZipOutputStream zos=new ZipOutputStream(fos);
        for(String a:contents){
            a=multiplefilespath+"//"+a;
            zos.putNextEntry(new ZipEntry(new File(a).getName()));

            byte[] bytes =Files.readAllBytes(Paths.get(a));
            zos.write(bytes,0,bytes.length);
            zos.closeEntry();
            System.out.println(a);
        }
        zos.close();

    }
    private static final int BUFFER_SIZE = 4096;
    @Test
    public void zipfolderplusfiles() throws IOException {
        String multiplefilespath=userdir+"\\src\\main\\java\\org\\example\\resources\\zipfiles\\files\\multiplefiles";
        File multiplefiles=new File(multiplefilespath);
        File[] allfiles=multiplefiles.listFiles();
        List<File> filelist=new ArrayList<File>();
        for(File file:allfiles){
            filelist.add(file);
        }
        zipname=userdir+"\\src\\main\\java\\org\\example\\resources\\zipfiles\\zippedfiles\\"+"fileplusfolder"+".zip";
        zip(filelist,zipname);
    }

    public void zip(List<File> listFiles, String destZipFile) throws FileNotFoundException,
            IOException {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(destZipFile));
        for (File file : listFiles) {
            if (file.isDirectory()) {
                zipDirectory(file, file.getName(), zos);
            } else {
                zipFile(file, zos);
            }
        }
        zos.flush();
        zos.close();
    }

    private void zipDirectory(File folder, String parentFolder,
                              ZipOutputStream zos) throws FileNotFoundException, IOException {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                zipDirectory(file, parentFolder + "/" + file.getName(), zos);
                continue;
            }
            zos.putNextEntry(new ZipEntry(parentFolder + "/" + file.getName()));
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(file));
            long bytesRead = 0;
            byte[] bytesIn = new byte[BUFFER_SIZE];
            int read = 0;
            while ((read = bis.read(bytesIn)) != -1) {
                zos.write(bytesIn, 0, read);
                bytesRead += read;
            }
            zos.closeEntry();
        }
    }
        private void zipFile(File file, ZipOutputStream zos)
            throws FileNotFoundException, IOException {
            zos.putNextEntry(new ZipEntry(file.getName()));
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
                    file));
            long bytesRead = 0;
            byte[] bytesIn = new byte[BUFFER_SIZE];
            int read = 0;
            while ((read = bis.read(bytesIn)) != -1) {
                zos.write(bytesIn, 0, read);
                bytesRead += read;
            }
            zos.closeEntry();
        }
    }



