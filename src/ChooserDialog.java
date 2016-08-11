import com.intellij.ide.util.TreeFileChooserFactory;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.newvfs.impl.VirtualFileImpl;
import com.intellij.psi.PsiFileFactory;
import com.intellij.psi.impl.PsiFileFactoryImpl;
import groovy.io.FileType;
import org.apache.http.util.TextUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Created by lixindong on 26/7/16.
 */
    public class ChooserDialog extends DialogWrapper {
    private JTextField tv_path;
    private JPanel container;
    private JButton btn;
    String mPath = "";

    @Override
    protected void doOKAction() {
        super.doOKAction();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (!TextUtils.isEmpty(mPath)) {
                    Utils.runShellCmd(mPath);
                } else {
                    System.out.println("path is empty");
                }
            }
        }).start();
    }

    protected ChooserDialog(@Nullable final Project project) {
        super(project);
        init();
        setTitle("Choose File...");
        setOKButtonText("Run");
        setCancelButtonText("Cancel");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mPath = showFileChooser(project);
                tv_path.setText(mPath);
            }
        });
        setOKActionEnabled(true);
    }

    public String showFileChooser(Project project) {
//        TreeFileChooserFactory factory = TreeFileChooserFactory.getInstance(project);
        FileChooserDescriptor descriptor = FileChooserDescriptorFactory.createSingleFileDescriptor("sh");
//        factory.createFileChooser("", PsiFileFactoryImpl.getInstance(project), );
        VirtualFile file = FileChooser.chooseFile(descriptor, project, null);
        System.out.println("path " + file.getPath());
        return file.getPath();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return container;
    }
}
