package comparator;

import javax.imageio.IIOException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class ReadFile {
    private Path file;
    private List<String> list;

    ReadFile(String file){
        this.file = Paths.get(file);
    }

    void readFile(){
        try {
            //new BufferedReader(new InputStreamReader(new FileInputStream("a.txt"),"utf-8"));
            // list = Files.readAllLines(file);
            //list = Files.readAllLines(file, Charset.forName("windows-1251"));
            list = Files.readAllLines(file, StandardCharsets.UTF_8);
        }catch (MalformedInputException e){
            try {
                list = Files.readAllLines(file, Charset.forName("windows-1251"));
            } catch (IOException y){
                y.getMessage();
            }

            //System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        return "ReadFile{" +
                "file=" + file +
                ", list=" + list +
                '}';
    }

    List<String> getList() {
        return list;
    }
}

