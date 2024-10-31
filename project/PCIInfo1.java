import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PCIInfo1 {

    private List<String> pciDevices;

    public PCIInfo1() {
        pciDevices = new ArrayList<>();
    }

    // Executes the `lspci` command and reads its output
    public void read() {
        try {
            // Use ProcessBuilder to execute the lspci command
            ProcessBuilder builder = new ProcessBuilder("lspci", "-nn");
            Process process = builder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            // Read each line of lspci output
            while ((line = reader.readLine()) != null) {
                pciDevices.add(parseDeviceInfo(line));
            }

            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Returns all PCI devices as a list of strings
    public List<String> getDevices() {
        return pciDevices;
    }

    // Parses a single line of `lspci` output to extract readable PCI information
    private String parseDeviceInfo(String line) {
        // Example line: "00:1f.2 SATA controller [8086:2929] Intel Corporation 82801IR/IO Controller Hub (ICH9R)"
        String[] parts = line.split(" ");
        String busDevice = parts[0];

        // Extract vendor and product ID
        String vendorProductID = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
        String description = line.substring(line.indexOf("]") + 1).trim();

        // Format the information for better readability
        return String.format(
                "Bus/Device: %s\nVendor/Product ID: %s\nDescription: %s",
                busDevice,
                vendorProductID,
                description
        );
    }
}
