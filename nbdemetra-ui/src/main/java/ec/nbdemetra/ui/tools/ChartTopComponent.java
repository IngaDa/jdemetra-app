/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.nbdemetra.ui.tools;

import ec.nbdemetra.ui.DemetraUI;
import ec.nbdemetra.ui.DemetraUiIcon;
import ec.nbdemetra.ui.NbComponents;
import ec.nbdemetra.ui.nodes.ControlNode;
import demetra.ui.components.JTsChart;
import internal.ui.components.HasColorSchemeCommands;
import internal.ui.components.HasTsCollectionCommands;
import java.awt.BorderLayout;
import javax.swing.*;
import org.netbeans.api.settings.ConvertAsProperties;
import org.netbeans.core.spi.multiview.CloseOperationState;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.MultiViewElementCallback;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.DropDownButtonFactory;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.nodes.*;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.Mode;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//ec.nbdemetra.ui.tools//Chart//EN",
        autostore = false)
@TopComponent.Description(preferredID = "ChartTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ONLY_OPENED)
@TopComponent.Registration(mode = "output", openAtStartup = false)
@ActionID(category = "Window", id = "ec.nbdemetra.ui.tools.ChartTopComponent")
@ActionReference(path = "Menu/Tools/Container", position = 100)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ChartAction")
@Messages({
    "CTL_ChartAction=Chart",
    "CTL_ChartTopComponent=Chart",
    "HINT_ChartTopComponent=This is a Chart window"
})
public final class ChartTopComponent extends TopComponent implements ExplorerManager.Provider, MultiViewElement {

    private final ExplorerManager mgr = new ExplorerManager();

    public ChartTopComponent() {
        initComponents();
        setName(Bundle.CTL_ChartTopComponent());
        setToolTipText(Bundle.HINT_ChartTopComponent());
        associateLookup(ExplorerUtils.createLookup(mgr, getActionMap()));
        add(new JTsChart(), BorderLayout.CENTER);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void open() {
        super.open();
        WindowManager.getDefault().getModes();
        Mode mode = WindowManager.getDefault().findMode("output");
        if (mode != null) {
            mode.dockInto(this);
        }
    }

    @Override
    public void componentOpened() {
        ControlNode.onComponentOpened(mgr, getChart());
//        Sheet.Set properties = Sheet.createPropertiesSet();
//        properties.put(new Truc());
//        sheet.put(properties);
//
//        sheet.put(jTsChart1.isTitleVisible() ? s1 : s2);
//
//        try {
//            Node bn = new AbstractNode(Children.LEAF) {
//                @Override
//                protected Sheet createSheet() {
//                    return sheet;
//                }
//            };
//            mgr.setRootContext(bn);
//            mgr.setSelectedNodes(new Node[]{bn});
//        } catch (Exception ex) {
//            Exceptions.printStackTrace(ex);
//        }
    }
//    Sheet sheet = Sheet.createDefault();
//    Sheet.Set s1 = new NodePropertySetBuilder().name("S1").with(String.class).select("Title", "yo!").add().build();
//    Sheet.Set s2 = new NodePropertySetBuilder().name("S2").with(String.class).select("Autre", "1234567").add().build();

    @Override
    public JComponent getVisualRepresentation() {
        return this;
    }

    @Override
    public JComponent getToolbarRepresentation() {
        JToolBar result = NbComponents.newInnerToolbar();

        result.addSeparator();

        JButton copyBtn = new JButton(getChart().getActionMap().get(HasTsCollectionCommands.COPY_ALL_ACTION));
        copyBtn.setText("");
        copyBtn.setToolTipText("Copy");
        copyBtn.setIcon(DemetraUiIcon.EDIT_COPY_16);
        result.add(copyBtn);

        JPopupMenu menuColorScheme = HasColorSchemeCommands.menuOf(getChart(), DemetraUI.getDefault().getColorSchemes()).getPopupMenu();
        JButton coloSchemeBtn = DropDownButtonFactory.createDropDownButton(DemetraUiIcon.COLOR_SWATCH_16, menuColorScheme);
        coloSchemeBtn.addActionListener(getChart().getActionMap().get(HasColorSchemeCommands.DEFAULT_COLOR_SCHEME_ACTION));
        result.add(coloSchemeBtn);

        return result;
    }

    @Override
    public void setMultiViewCallback(MultiViewElementCallback callback) {
    }

    @Override
    public void componentDeactivated() {
        super.componentDeactivated();
    }

    @Override
    public void componentActivated() {
        super.componentActivated();
    }

    @Override
    public void componentHidden() {
        super.componentHidden();
    }

    @Override
    public void componentShowing() {
        super.componentShowing();
    }

    @Override
    public CloseOperationState canCloseElement() {
        return CloseOperationState.STATE_OK;
    }

//    class Truc extends Property<Boolean> {
//
//        public Truc() {
//            super(Boolean.class);
//        }
//
//        @Override
//        public boolean canRead() {
//            return true;
//        }
//
//        @Override
//        public Boolean getValue() throws IllegalAccessException, InvocationTargetException {
//            return jTsChart1.isTitleVisible();
//        }
//
//        @Override
//        public boolean canWrite() {
//            return true;
//        }
//
//        @Override
//        public void setValue(Boolean val) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//            jTsChart1.setTitleVisible(val);
//            sheet.remove("S1");
//            sheet.remove("S2");
//            sheet.put(jTsChart1.isTitleVisible() ? s1 : s2);
//        }
//    }
    @Override
    public void componentClosed() {
        mgr.setRootContext(Node.EMPTY);
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        ToolsPersistence.writeTsCollection(getChart(), p);
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        ToolsPersistence.readTsCollection(getChart(), p);
    }

    @Override
    public ExplorerManager getExplorerManager() {
        return mgr;
    }

    public JTsChart getChart() {
        return (JTsChart) getComponent(0);
    }
}
