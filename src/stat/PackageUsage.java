package stat;

import java.util.HashMap;
import java.util.Map;

public class PackageUsage {

    private Map<String, Integer> packages = new HashMap<>();
    private PackageStat root;

    public PackageUsage(PackageStat root) {
        this.root = root;
        putMap(root);
        findUsage(root);

    }

    private void putMap(PackageStat root) {
        packages.put(root.getPackageName(), 0);
        for(PackageStat ps : root.getSubPackages()) {
            putMap(ps);
        }
    }

    private void findUsage(PackageStat root) {
        for(ClassStat cs : root.getClasses()) {
            for(String s : cs.getImportPackages()) {
                if(packages.containsKey(s)) {
                    int i = packages.get(s);
                    i++;
                    packages.put(s, i);
                }
            }
        }
        for(PackageStat ps : root.getSubPackages()) {
            findUsage(ps);
        }
    }

    public Map<String, Integer> getPackages() {
        return packages;
    }

    public PackageStat getRoot() {
        return root;
    }
}
