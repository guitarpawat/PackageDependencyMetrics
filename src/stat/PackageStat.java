package stat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PackageStat {

    private int abstracts = 0;
    private int imports = 0;
    private String packageName = "";
    private List<PackageStat> subPackages = new ArrayList<>();
    private List<ClassStat> classes = new ArrayList<>();

    public PackageStat(String dir) {
        this(new File(dir));
    }

    public PackageStat(File file) {
        File[] files = file.listFiles();
        for(File f : files) {
            if(f.isDirectory()) {
                PackageStat ps = new PackageStat(f);
                getSubPackages().add(ps);
            } else {
                String[] fn = f.getName().split("\\.");
                if(fn[fn.length - 1].equalsIgnoreCase("java")) {
                    ClassStat cs = new ClassStat(f);
                    getClasses().add(cs);
                }
            }
        }
        for(ClassStat cs : getClasses()) {
            if(cs.isAbstractClass()) {
                abstracts = getAbstracts() + 1;
            }
            imports = getImports() + cs.getImportPackages().size();
            if(getPackageName().isEmpty()) {
                packageName = cs.getPackageName();
            }
        }
    }

    public int getAbstracts() {
        return abstracts;
    }

    public int getImports() {
        return imports;
    }

    public String getPackageName() {
        return packageName;
    }

    public List<PackageStat> getSubPackages() {
        return subPackages;
    }

    public List<ClassStat> getClasses() {
        return classes;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(!(obj instanceof PackageStat)) return false;
        PackageStat ps = (PackageStat) obj;
        return getPackageName().equals(ps.getPackageName());
    }
}
