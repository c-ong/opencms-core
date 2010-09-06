/*
 * File   : $Source: /alkacon/cvs/opencms/src-modules/org/opencms/ade/galleries/client/preview/ui/Attic/A_CmsPreviewDetailTab.java,v $
 * Date   : $Date: 2010/08/26 13:34:11 $
 * Version: $Revision: 1.2 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Management System
 *
 * Copyright (C) 2002 - 2009 Alkacon Software (http://www.alkacon.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * For further information about Alkacon Software, please see the
 * company website: http://www.alkacon.com
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.opencms.ade.galleries.client.preview.ui;

import org.opencms.ade.galleries.client.preview.I_CmsPreviewHandler;
import org.opencms.ade.galleries.client.ui.Messages;
import org.opencms.ade.galleries.shared.I_CmsGalleryProviderConstants.GalleryMode;
import org.opencms.gwt.client.ui.CmsPushButton;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * Basic preview detail tab layout.<p>
 * 
 * @author Tobias Herrmann
 * 
 * @version $Revision: 1.2 $
 * 
 * @since 8.0.
 */
public abstract class A_CmsPreviewDetailTab extends Composite {

    /**
     * @see com.google.gwt.uibinder.client.UiBinder
     */
    protected interface I_CmsPreviewDetailTabUiBinder extends UiBinder<FlowPanel, A_CmsPreviewDetailTab> {
        // gwt interface, nothing to do
    }

    /** The ui-binder instance for this class. */
    private static I_CmsPreviewDetailTabUiBinder uiBinder = GWT.create(I_CmsPreviewDetailTabUiBinder.class);

    /** The button panel. */
    @UiField
    protected FlowPanel m_buttonBar;

    /** The main panel. */
    protected FlowPanel m_main;

    /** The select button. */
    @UiField
    protected CmsPushButton m_selectButton;

    /** The tab height. */
    protected int m_tabHeight;

    /** The tab width. */
    protected int m_tabWidth;

    /** Flag indicating if tab content has been changed and will need saving. */
    private boolean m_changed;

    /** The mode of the gallery. */
    private GalleryMode m_dialogMode;

    /**
     * Constructor.<p>
     * 
     * @param dialogMode the gallery mode
     * @param height the tab height
     * @param width the tab width
     */
    public A_CmsPreviewDetailTab(GalleryMode dialogMode, int height, int width) {

        m_main = uiBinder.createAndBindUi(this);
        initWidget(m_main);

        m_dialogMode = dialogMode;
        m_tabHeight = height;
        m_tabWidth = width;
        // buttons        
        switch (m_dialogMode) {
            case widget:
                m_selectButton.setText(Messages.get().key(Messages.GUI_PREVIEW_BUTTON_SELECT_0));
                break;
            case editor:
            case sitemap:
            case ade:
            case view:
            default:
                m_selectButton.setVisible(false);
                break;
        }

    }

    /**
     * Returns if the given tab has changes that need saving.<p>
     * 
     * @return <code>true</code> if the given tab has changes that need saving
     */
    public boolean isChanged() {

        return m_changed;
    }

    /**
     * Will be triggered, when the select button is clicked.<p>
     * 
     * @param event the click event
     */
    @UiHandler("m_selectButton")
    public void onSelectClick(ClickEvent event) {

        getHandler().selectResource();
    }

    /**
     * Returns the gallery mode.<p>
     * 
     * @return the gallery mode
     */
    protected GalleryMode getDialogMode() {

        return m_dialogMode;
    }

    /**
     * Returns the preview handler.<p>
     * 
     * @return the preview handler
     */
    protected abstract I_CmsPreviewHandler<?> getHandler();

    /**
     * Sets the changed state of the tab.<p>
     * 
     * @param changed the changed state
     */
    protected void setChanged(boolean changed) {

        m_changed = changed;
    }

}