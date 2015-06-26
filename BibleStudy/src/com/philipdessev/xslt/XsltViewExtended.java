package com.philipdessev.xslt;

import java.util.Locale;

public class XsltViewExtended extends org.springframework.web.servlet.view.xslt.XsltView {

    @Override
    public boolean checkResource(Locale locale) throws Exception {
        if (getServletContext().getResource(getUrl()) != null) {
            return true;
        }
        return false;
    }
}