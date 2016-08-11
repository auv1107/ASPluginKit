import com.intellij.openapi.ui.Messages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by lixindong on 26/7/16.
 */
public class Utils {
    public static void runShellCmd(String path) {
        try {
            Process ps = Runtime.getRuntime().exec(new String[] {"bash", path});
            System.out.println("running");
            long startTime = System.currentTimeMillis();
            ps.waitFor();
            System.out.println("stop");
            System.out.println("total running time: " + (System.currentTimeMillis() - startTime));
            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            String result = sb.toString();

//            Messages.showDialog(result, "Result: ", new String[]{"OK"}, -1, null);
            System.out.println(result);


        } catch (IOException e1) {
            e1.printStackTrace();
//            Messages.showDialog("IOException", "Result:", new String[]{"OK"}, -1, null);
            System.out.println("IOException");
        } catch (InterruptedException e1) {
            e1.printStackTrace();
//            Messages.showDialog("InterruptedException", "Result:", new String[]{"OK"}, -1, null);
            System.out.println("InterruptedException");
        }
    }
}
