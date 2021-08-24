package com.revature.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.revature.model.VideoGame;
import com.revature.orm.util.ORM;
import com.revature.service.VideoGameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Servlet listener
 * listens for when server is created and creates the servlets
 */
@WebListener
public class ServletDriver implements ServletContextListener
{
    private static final Logger logger = LoggerFactory.getLogger(VideoGameService.class);

    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        List<Class> classList = new ArrayList<>();
        classList.add(VideoGame.class);

        Properties properties = new Properties();

        try
        {
            FileReader propertyReader = new FileReader(ServletDriver.class.getClassLoader()
                    .getResource("application.properties").getPath()
                    .replaceAll("%20", " "));
            properties.load(propertyReader);
        }
        catch (IOException e)
        {
            logger.warn(e.getMessage(), e);
        }
        ORM videoGameORM = new ORM(
                properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password"),
                classList);

        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        VideoGameService videoGameService = new VideoGameService(videoGameORM, objectMapper);

        ServletContext servletContext = sce.getServletContext();
        servletContext.addServlet("videoGameServlet", new VideoGameServlet(videoGameService)).addMapping("/videogames");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
