package com.dreamlee;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.util.List;

/**
 * @author: DreamLee
 * @date: Created on 15:21 2018/12/16
 * @description: 找出baseDir目录下以type字符串为后缀的文件并计数
 * @modified:
 */
@Mojo(name = "findfiles", defaultPhase = LifecyclePhase.PACKAGE)
public class FindFilesMojo extends AbstractMojo {

    @Parameter
    private String baseDir;

    @Parameter(property = "type")
    private String type;

    private int count;

    //private List<File> fileList;

    public void execute() throws MojoExecutionException, MojoFailureException {
        if (null == type)
            type = "java";
        System.out.println(findFiles() + " " + type +" files have been found!");
        //System.out.println("files have been found! They are " + fileList);
    }

    public int findFiles() {
        File file = new File(baseDir);
        if (null == file)
            throw new RuntimeException("The path of file is invalide.");
        else {
            find(file);
            return count;
        }
    }

    private void find(File baseFile) {
        File[] files = baseFile.listFiles();
        if (null != files && files.length != 0)
            for (File file : files) {
                if (!file.isDirectory()) {
                    int index = file.toString().lastIndexOf(".") + 1;
                    String substring = file.toString().substring(index);
                    if (substring.equalsIgnoreCase(type)) {
                        //fileList.add(file);
                        count++;
                    }
                }
                else
                    find(file);
            }
    }

}
