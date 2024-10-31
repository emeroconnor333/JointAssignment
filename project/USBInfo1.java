import java.util.HashMap;
import java.util.Map;

public class USBInfo1 {

    // Method to display USB information
    public static String showUSB() {
        StringBuilder result = new StringBuilder();  // StringBuilder to accumulate results

        // Creating the vendor lookup table
        Map<String, String> vendorMap = createVendorMap();
        // Creating the product lookup table
        Map<String, String> productMap = createProductMap();

        try {
            System.loadLibrary("sysinfo");  // Load the required native library "sysinfo"
            usbInfo usb = new usbInfo();  // Create an instance of usbInfo to interact with USB hardware info
            usb.read();  // Read information about connected USB devices

            int busCount = usb.busCount();  // Get the number of USB buses available
            if (busCount == 0) {  // If no USB buses are detected, return a message indicating that
                return "There are no USB buses detected.";
            }

            // Add headers for the USB information table
            result.append(String.format("%-10s %-10s %-45s %-45s%n", "Bus", "Device", "Vendor", "Product"));
            result.append("----------------------------------------------------------------------------------------------------------------------------------------\n");

            boolean deviceFound = false;  // Flag to check if any USB devices are found

            // Iterate over each USB bus
            for (int i = 1; i <= busCount; i++) {
                int deviceCount = usb.deviceCount(i);  // Get the number of devices on the current bus
                for (int j = 1; j <= deviceCount; j++) {  // Iterate over each device on the current bus
                    deviceFound = true;  // Mark that at least one device has been found

                    // Get vendor ID and product ID
                    String vendorId = String.format("0x%04X", usb.vendorID(i, j));
                    String productId = String.format("0x%04X", usb.productID(i, j));

                    // Look up vendor name, or set as unidentified if not found
                    String vendorName = vendorMap.getOrDefault(vendorId, "Unidentified Vendor");

                    // Look up product name, or set as unidentified if not found
                    String productName = productMap.getOrDefault(productId, "Unidentified Product");

                    // Combine vendor name and vendor ID for display
                    String vendorInfo = String.format("%s (%s)", vendorName, vendorId);

                    // Combine product name and product ID for display
                    String productInfo = String.format("%s (%s)", productName, productId);

                    // Append the USB device information to the result
                    result.append(String.format("%-10d %-10d %-45s %-45s%n",
                            i,  // Bus number
                            j,  // Device number
                            vendorInfo,  // Vendor name and ID
                            productInfo  // Product name and ID
                    ));
                }
            }

            // If no devices were found on any bus, append a message indicating that
            if (!deviceFound) {
                result.append("No USB devices are connected.");
            }

        } catch (Exception e) {
            e.printStackTrace();  // Print stack trace in case of exceptions for debugging
            return "Error reading USB information: " + e.getMessage();  // Return error message if exception occurs
        }

        return result.toString();  // Return the accumulated USB information as a string
    }

    // Method to create a map of vendor IDs to vendor names
    private static Map<String, String> createVendorMap() {
        Map<String, String> vendorMap = new HashMap<>();

        // Populate with 100 example vendors, all with uppercase IDs
        vendorMap.put("0x8086", "Intel Corporation");
        vendorMap.put("0x10DE", "NVIDIA Corporation");
        vendorMap.put("0x1002", "Advanced Micro Devices, Inc. [AMD/ATI]");
        vendorMap.put("0x1425", "Chelsio Communications Inc");
        vendorMap.put("0x1093", "National Instruments");
        vendorMap.put("0x1D6B", "Linux Foundation");
        vendorMap.put("0x04E8", "Samsung Electronics Co., Ltd");
        vendorMap.put("0x046D", "Logitech, Inc.");
        vendorMap.put("0x045E", "Microsoft Corporation");
        vendorMap.put("0x093A", "Pixart Imaging, Inc.");
        vendorMap.put("0x05AC", "Apple, Inc.");
        vendorMap.put("0x1A40", "TERMINUS TECHNOLOGY INC.");
        vendorMap.put("0x1A86", "QinHeng Electronics");
        vendorMap.put("0x0409", "NEC Corporation");
        vendorMap.put("0x03F0", "Hewlett-Packard");
        vendorMap.put("0x0BDA", "Realtek Semiconductor Corp.");
        vendorMap.put("0x0A5C", "Broadcom Corp.");
        vendorMap.put("0x1C7A", "Etron Technology, Inc.");
        vendorMap.put("0x07D1", "D-Link System");
        vendorMap.put("0x2109", "VIA Labs, Inc.");
        vendorMap.put("0x0483", "STMicroelectronics");
        vendorMap.put("0x05E3", "Genesys Logic, Inc.");
        vendorMap.put("0x1D4D", "GIGABYTE");
        vendorMap.put("0x03EB", "Atmel Corp.");
        vendorMap.put("0x18D1", "Google Inc.");
        vendorMap.put("0x12D1", "Huawei Technologies Co., Ltd.");
        vendorMap.put("0x0451", "Texas Instruments, Inc.");
        vendorMap.put("0x04A9", "Canon, Inc.");
        vendorMap.put("0x04B3", "IBM Corp.");
        vendorMap.put("0x0BB4", "HTC (High Tech Computer Corp.)");
        vendorMap.put("0x0930", "Toshiba Corp.");
        vendorMap.put("0x0C45", "Microdia");
        vendorMap.put("0x090C", "Silicon Motion, Inc.");
        vendorMap.put("0x054C", "Sony Corporation");
        vendorMap.put("0x0951", "Kingston Technology");
        vendorMap.put("0x0CF3", "Qualcomm Atheros Communications");
        vendorMap.put("0x058F", "Alcor Micro Corp.");
        vendorMap.put("0x17EF", "Lenovo");
        vendorMap.put("0x0E8F", "MediaTek Inc.");
        vendorMap.put("0x0A12", "Cambridge Silicon Radio, Ltd");
        vendorMap.put("0x0781", "SanDisk Corp.");
        vendorMap.put("0x03EE", "Fujitsu Ltd.");
        vendorMap.put("0x056A", "Wacom Co., Ltd");
        vendorMap.put("0x04D8", "Microchip Technology Inc.");
        vendorMap.put("0x04BB", "Seiko Epson Corp.");
        vendorMap.put("0x0586", "ZyXEL Communications Corp.");
        vendorMap.put("0x1397", "Sunplus Technology Co., Ltd");
        vendorMap.put("0x0583", "Padix Co., Ltd.");
        vendorMap.put("0x13FE", "Kingston Digital, Inc.");
        vendorMap.put("0x04B8", "Epson Imaging Technology Center");
        vendorMap.put("0x12BA", "AIMEX Corporation");
        vendorMap.put("0x04C5", "Fujitsu Ltd.");
        vendorMap.put("0x0BDB", "Ericsson Business Mobile Networks BV");
        vendorMap.put("0x1B1C", "Corsair");
        vendorMap.put("0x0E21", "Cowon Systems, Inc.");
        vendorMap.put("0x03FE", "Matrix Vision GmbH");
        vendorMap.put("0x0402", "ALi Corp.");
        vendorMap.put("0x1344", "Olympus Optical Co., Ltd");
        vendorMap.put("0x05DC", "Lexar Media, Inc.");
        vendorMap.put("0x1410", "Novatel Wireless");
        vendorMap.put("0x0489", "Foxconn / Hon Hai");
        vendorMap.put("0x0711", "Buffalo Inc.");
        vendorMap.put("0x0547", "Anchor Chips Inc.");
        vendorMap.put("0x03FC", "L-3 Communications");
        vendorMap.put("0x04B9", "KeyTronic Corp.");
        vendorMap.put("0x1532", "Razer USA, Ltd");
        vendorMap.put("0x04F2", "Chicony Electronics Co., Ltd");
        vendorMap.put("0x17A0", "MosArt Semiconductor Corp.");
        vendorMap.put("0x0458", "KYE Systems Corp. (Mouse Systems)");
        vendorMap.put("0x0789", "Shenzhen Goodix Technology Co., Ltd");
        vendorMap.put("0x03DE", "BenQ Corporation");
        vendorMap.put("0x059B", "Iomega Corp.");
        vendorMap.put("0x10C4", "Silicon Labs");
        vendorMap.put("0x0424", "SMSC");
        vendorMap.put("0x1A81", "Holtek Semiconductor, Inc.");
        vendorMap.put("0x05F3", "PI Engineering, Inc.");
        vendorMap.put("0x0461", "Primax Electronics, Ltd");
        vendorMap.put("0x04FF", "Sunplus Technology Co., Ltd");
        vendorMap.put("0x0764", "Cyber Power Systems, Inc.");
        vendorMap.put("0x15CA", "Textech International Ltd.");
        vendorMap.put("0x17F4", "N-Trig");
        vendorMap.put("0x04CC", "ST-Ericsson");
        vendorMap.put("0x046A", "Cherry GmbH");
        vendorMap.put("0x1A2C", "China Resource Semico Co., Ltd");
        vendorMap.put("0x80EE", "Oracle Corporation (VirtualBox)");

        // Add more entries to complete the vendor map

        return vendorMap;
    }

    // Method to create a map of product IDs to product names
    private static Map<String, String> createProductMap() {
        Map<String, String> productMap = new HashMap<>();

        // Populate with 100 example products, all with uppercase IDs
        productMap.put("0x8139", "AT-2500TX V3 Ethernet");
        productMap.put("0x7A00", "Hyper Transport Bridge Controller");
        productMap.put("0x7A02", "APB (Advanced Peripheral Bus) Controller");
        productMap.put("0x7A03", "Gigabit Ethernet Controller");
        productMap.put("0x7A04", "OTG USB Controller");
        productMap.put("0x1234", "Standard USB Keyboard");
        productMap.put("0x5678", "HD Webcam");
        productMap.put("0x9ABC", "Wireless Bluetooth Adapter");
        productMap.put("0xDEF0", "USB 3.0 Flash Drive");
        productMap.put("0x0022", "USB-C Multiport Adapter");
        productMap.put("0x1235", "Ethernet Network Adapter");
        productMap.put("0x5679", "Portable External SSD");
        productMap.put("0x9ABD", "Wireless Gaming Mouse");
        productMap.put("0xDEF1", "Mechanical Keyboard");
        productMap.put("0x0023", "USB to Serial Adapter");
        productMap.put("0x12F5", "Portable Projector");
        productMap.put("0x56A9", "Fitness Band");
        productMap.put("0x9ACF", "Portable Charger");
        productMap.put("0xDEF3", "Smart Card Reader");
        productMap.put("0x0034", "Virtual Reality Headset");
        productMap.put("0x0045", "USB Graphics Adapter");
        productMap.put("0x0156", "Fingerprint Reader");
        productMap.put("0x0167", "Thermal Printer");
        productMap.put("0x0289", "Digital Audio Interface");
        productMap.put("0x038A", "Wireless Access Point");
        productMap.put("0x049B", "Game Controller");
        productMap.put("0x051C", "USB Fan");
        productMap.put("0x062D", "USB LED Light");
        productMap.put("0x073E", "USB Microphone");
        productMap.put("0x084F", "USB Camera Module");
        productMap.put("0x0950", "Graphics Tablet");
        productMap.put("0x1061", "USB Hub 4-Port");
        productMap.put("0x1172", "USB Smart Plug");
        productMap.put("0x1283", "External Sound Card");
        productMap.put("0x1394", "Firewire Adapter");
        productMap.put("0x1415", "USB RFID Reader");
        productMap.put("0x1526", "Portable DVD Drive");
        productMap.put("0x1637", "USB Barcode Scanner");
        productMap.put("0x1748", "Digital Voice Recorder");
        productMap.put("0x1859", "USB Oscilloscope");
        productMap.put("0x196A", "USB DVB-T Tuner");
        productMap.put("0x1A7B", "Network Storage Adapter");
        productMap.put("0x1B8C", "External Blu-Ray Drive");
        productMap.put("0x1C9D", "USB Thermometer");
        productMap.put("0x1DAE", "Laser Rangefinder");
        productMap.put("0x1EBF", "USB Power Monitor");
        productMap.put("0x1FC0", "Solar Battery Charger");
        productMap.put("0x20D1", "3D Printer Controller");
        productMap.put("0x21E2", "USB KVM Switch");
        productMap.put("0x22F3", "POS Terminal Interface");
        productMap.put("0x23G4", "Fingerprint USB Stick");
        productMap.put("0x24H5", "Analog TV Tuner");
        productMap.put("0x25I6", "USB Temperature Data Logger");
        productMap.put("0x26J7", "Wireless Presentation Remote");
        productMap.put("0x27K8", "Gesture Controller");
        productMap.put("0x28L9", "Portable Credit Card Reader");
        productMap.put("0x29M0", "USB Sound Level Meter");
        productMap.put("0x2AF1", "Mini Drone Controller");
        productMap.put("0x2B12", "WiFi Range Extender");
        productMap.put("0x2C23", "Bluetooth Audio Receiver");
        productMap.put("0x2D34", "USB MIDI Interface");
        productMap.put("0x2E45", "USB CO2 Sensor");
        productMap.put("0x2F56", "RGB Light Strip Controller");
        productMap.put("0x3057", "USB Smart Door Lock");
        productMap.put("0x3168", "USB 5G Dongle");
        productMap.put("0x3279", "Arduino USB Controller");
        productMap.put("0x338A", "USB Finger Vein Scanner");
        productMap.put("0x349B", "USB Spectrum Analyzer");
        productMap.put("0x35AC", "PC VR Hand Controller");
        productMap.put("0x36BD", "USB Digital Picture Frame");
        productMap.put("0x37CE", "USB Portable Fan Heater");
        productMap.put("0x38DF", "USB Plant Monitor");
        productMap.put("0x39E0", "USB Motion Sensor");
        productMap.put("0x40F1", "Automotive OBD-II Adapter");
        productMap.put("0x41F2", "USB Pulse Oximeter");
        productMap.put("0x42F3", "Smartphone Gimbal");
        productMap.put("0x43F4", "Car Dashcam");
        productMap.put("0x44F5", "USB Solar Charger");
        productMap.put("0x45F6", "USB Backup Battery");
        productMap.put("0x46F7", "UV Light Sterilizer");
        productMap.put("0x47F8", "Pocket Photo Printer");
        productMap.put("0x48F9", "Portable Hard Disk Enclosure");
        productMap.put("0x49FA", "Smart Weight Scale");
        productMap.put("0x50FB", "USB Heating Pad");
        productMap.put("0x51FC", "USB Magnetic Stripe Reader");
        productMap.put("0x52FD", "Wireless Earbuds Case");
        productMap.put("0x53FE", "Fitness Smart Ring");
        productMap.put("0x54FF", "USB Desoldering Station");
        productMap.put("0x5600", "Portable Gaming Console");
        productMap.put("0x5701", "USB Infrared Thermometer");
        productMap.put("0x5802", "Interactive Whiteboard Adapter");
        productMap.put("0x5903", "USB Vibration Sensor");
        productMap.put("0x0002", "USB Root Hub");
        productMap.put("0x0010", "Ethernet Adapter Product");
        productMap.put("0x0021", "USB Mass Storage Device");
        productMap.put("0x0003", "USB Composite Device");

        return productMap;
    }
}
