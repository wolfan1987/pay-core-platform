package org.andrew.commons.ecard.listener;


import org.andrew.commons.ecard.common.H5VersionConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 静态文件版本控制。
 *
 * @author andrewliu
 */
public class H5VersionListener implements ServletContextListener {

    private static final Logger logger = LoggerFactory.getLogger(H5VersionListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String h5Version = H5VersionConfig.VERSION;
        logger.info("应用启动，获取静态资源版本号：{}", h5Version);
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("version", h5Version);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
