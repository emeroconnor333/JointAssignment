public class TestingMethods {
    public static void showCPU()
    {
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);

        System.out.println("-CPU Info-");
        System.out.println("Cores per socket: " + cpu.coresPerSocket());
        System.out.println("Socket count: " + cpu.socketCount());
        System.out.print("Model number: " + cpu.getModel());
        System.out.println("L1 instruction cache size: " + cpu.l1iCacheSize() / 1024 + "KB");
        System.out.println("L2 cache size: " + cpu.l2CacheSize() / 1048576 + "MB");
        System.out.println("L3 cache size: " + cpu.l3CacheSize() / 1048576 + "MB");
        System.out.println("Core 0 time spent in user mode: " + cpu.getUserTime(0) + " jiffies");
        System.out.println("Core 0 time spent in idle: " + cpu.getIdleTime(0) + " jiffies");
        System.out.println("Core 0 time spent in system mode: " + cpu.getSystemTime(0) + " jiffies");
    }

    public static void showPCI()
    {
        pciInfo pci = new pciInfo();
        pci.read();

        System.out.printf("%n-PCI Info-%n");
        System.out.println("Number of buses: " + pci.busCount());
        System.out.println("Number of devices on bus 0: " + pci.deviceCount(0));
        System.out.println("Number of functions on bus 0, device 0: " + pci.functionCount(0, 0));
        System.out.println("Bus 0, device 0, function 0 is present: " + pci.functionPresent(0,0,0));
        System.out.println("Vendor ID of bus 0, device 0, function 0: " + pci.vendorID(0, 0, 0));
        System.out.println("Product ID of bus 0, device 0, function 0: " + pci.productID(0, 0, 0));
    }

    public static void showUSB()
    {
        usbInfo usb = new usbInfo();
        usb.read();

        System.out.printf("%n-USB Info-%n");
        System.out.println("Number of USB buses: " + usb.busCount());
        System.out.println("Number of devices on USB 0: " + usb.deviceCount(0));
        System.out.println("Vendor ID of bus 0, device 0: " + usb.vendorID(0, 0));
        System.out.println("Product ID of bus 0, device 0: " + usb.productID(0, 0));
    }

    public static void main(String[] args) {
        System.loadLibrary("sysinfo");
        sysInfo info = new sysInfo();
        cpuInfo cpu = new cpuInfo();
        cpu.read(0);

        showCPU();
        showPCI();
        showUSB();
    }
}

