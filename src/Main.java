import stat.PackageStat;
import stat.PackageUsage;

import java.util.Map;
import java.util.Scanner;

public class Main {

    static Map<String, Integer> usage;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String path = s.nextLine();
        PackageStat stat = new PackageStat(path);
        PackageUsage u = new PackageUsage(stat);
        usage = u.getPackages();
        print(stat);
    }

    public static void print(PackageStat root) {
        printStat(root);
        for(PackageStat ps : root.getSubPackages()) {
            if(ps.getPackageName().isEmpty()) continue;
            System.out.println("===========================================");
            print(ps);
        }
    }

    public static void printStat(PackageStat ps) {
        System.out.println("Package name: "+ps.getPackageName());
        System.out.println("No. of total class: "+ps.getClasses().size());
        System.out.println("No. of abstract class: "+ps.getAbstracts());
        System.out.println("No. of imports: "+ps.getImports());
        System.out.println("No. of class uses: "+usage.getOrDefault(ps.getPackageName(), 0));
    }
}
