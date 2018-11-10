public class PackageInfo {

    String packageName;
    
    double ca;
    double ce;
    
    double na;
    double nc;


    public PackageInfo(String packageName, double ca,double ce,double na, double nc){
    	this.ca = ca;
    	this.ce = ce;
    	this.na = na;
    	this.nc = nc;
    	this.packageName = packageName;
    }
    
    public double getInstability(){
        return ce/(ca+ce);
    }

    public double getAbstactness(){
        return na/nc;
    }
    
    public String toString() {
    	return packageName;
    }
    
    public double getDdass() {
    	return Math.abs(getInstability() + getAbstactness() - 1);
    }

}