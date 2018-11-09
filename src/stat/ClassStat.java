package stat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClassStat {

    private boolean abstractClass = false;
    private List<String> importPackages = new ArrayList<>();
    private String className = "";
    private String packageName = "";

    public ClassStat(String fileDir) {
        this(new File(fileDir));
    }

    public ClassStat(File file) {
        className = file.getName().split("\\.")[0];

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            findKeyword(br);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void findKeyword(BufferedReader br) throws IOException {
        String line;
        while (true) {
            line = br.readLine();
            if(line == null) break;
            line = line.trim().replace(";", "");
            String[] token = line.split(" ");
            if(token.length == 0) break;

            if(token[0].equals("package") && packageName.isEmpty()) {
                packageName = token[token.length - 1];
            } else if(token.length > 3 && token[0].equals("public") &&
                    token[1].equals("abstract") && token[2].equals("class")) {
                abstractClass = true;
            } else if(token.length > 2 && token[0].equals("public") &&
                    token[1].equals("interface")) {
                abstractClass = true;
            } else if(token.length > 2 && token[0].equals("import") &&
                    token[1].equals("static")) {
                String c = removeClass(token[token.length - 1]);
                if(!importPackages.contains(c)) {
                    importPackages.add(c);
                }
            } else if(token.length > 1 && token[0].equals("import")) {
                String c = removeClass(token[token.length - 1]);
                if(!importPackages.contains(c)) {
                    importPackages.add(c);
                }
            }
        }
    }

    private String removeClass(String s) {
        if(s == null || s.isEmpty()) return "";
        String[] pk = s.split("\\.");
        String res = "";
        for(int i=0; i<pk.length; i++) {
            if(Character.isUpperCase(pk[i].charAt(0)) || pk[i].equals("*")) {
                break;
            }
            if(i != 0) {
                res += ".";
            }
            res += pk[i];
        }

        return res;
    }

    public boolean isAbstractClass() {
        return abstractClass;
    }

    public List<String> getImportPackages() {
        return importPackages;
    }

    public String getClassName() {
        return className;
    }

    public String getPackageName() {
        return packageName;
    }
}
