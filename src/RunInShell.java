import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;

/**
 * Created by lixindong on 25/7/16.
 */
public class RunInShell extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        WindowManager manager = WindowManager.getInstance();
        Project project = e.getProject();
        StatusBar statusBar = manager.getStatusBar(project);
        statusBar.setInfo("Here is the result");
        ChooserDialog dialog = new ChooserDialog(project);
        dialog.show();
//        dialog.showFileChooser(project);

    }

}
