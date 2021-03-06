package com.revature.servlet;

import com.revature.service.VideoGameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for handling HTTP requests
 * all requests get passed to service layer
 */
public class VideoGameServlet extends HttpServlet
{
    private static final Logger logger = LoggerFactory.getLogger(VideoGameService.class);

    VideoGameService videoGameService;

    public VideoGameServlet(VideoGameService videoGameService)
    {
        this.videoGameService = videoGameService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        videoGameService.getVideoGame(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        videoGameService.insertVideoGame(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        videoGameService.updateVideoGame(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        videoGameService.deleteVideoGame(req, resp);
    }
}
