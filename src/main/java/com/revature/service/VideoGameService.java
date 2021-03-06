package com.revature.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.VideoGame;
import com.revature.orm.util.ORM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * service layer for interfacing between HTTP requests and Databases
 */
public class VideoGameService
{
    private static final Logger logger = LoggerFactory.getLogger(VideoGameService.class);

    private ObjectMapper objectMapper;
    private ORM orm;

    public VideoGameService(ORM orm, ObjectMapper objectMapper)
    {
        this.objectMapper = objectMapper;
        this.orm = orm;
    }

    /**
     * links GET request with SELECT
     * gives back all the records in the table
     * @param req HTTP request
     * @param resp HTTP response - contains status code and, if successful, list of VideoGames
     */
    public void getVideoGame(HttpServletRequest req, HttpServletResponse resp)
    {
        try
        {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(orm.getRecords("videogames"));
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getOutputStream().print(json);
        }
        catch (IOException e)
        {
            logger.warn(e.getMessage(), e);
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }

    /**
     * links POST request with INSERT
     * inserts a new video game into the database
     * @param req HTTP request - contains JSON of video game
     * @param resp HTTP response - containse status code
     */
    public void insertVideoGame(HttpServletRequest req, HttpServletResponse resp)
    {
        try
        {
            StringBuilder builder = new StringBuilder();
            req.getReader().lines()
                    .collect(Collectors.toList())
                    .forEach(builder::append);

            VideoGame newVideoGame = objectMapper.readValue(builder.toString(), VideoGame.class);
            boolean result = orm.addRecord(newVideoGame);

            if(result)
            {
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }
            else
            {
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
            }

        }
        catch (IOException e)
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            logger.warn(e.getMessage());
        }
    }

    /**
     * links PUT request with UPDATE
     * updates a record in the database
     * @param req HTTP request - contains JSON of video game to update
     * @param resp HTTP response - contains status code
     */
    public void updateVideoGame(HttpServletRequest req, HttpServletResponse resp)
    {
        try
        {
            StringBuilder builder = new StringBuilder();
            req.getReader().lines()
                    .collect(Collectors.toList())
                    .forEach(builder::append);

            VideoGame currentVideoGame = objectMapper.readValue(builder.toString(), VideoGame.class);
            boolean result = orm.updateRecord(currentVideoGame);

            if(result)
            {
                resp.setStatus(HttpServletResponse.SC_CREATED);
            }
            else
            {
                resp.setStatus(HttpServletResponse.SC_CONFLICT);
            }

        }
        catch (IOException e)
        {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            logger.warn(e.getMessage());
        }
    }

    /**
     * links DELETE request with DELETE
     * @param req HTTP request
     * @param resp HTTP response - contains status code
     */
    public void deleteVideoGame(HttpServletRequest req, HttpServletResponse resp)
    {
        int gameId = Integer.parseInt(req.getParameter("id"));
        VideoGame oldVideoGame = new VideoGame(gameId);
        boolean result = orm.deleteRecord(oldVideoGame);
        if(result)
        {
            resp.setStatus(HttpServletResponse.SC_CREATED);
        }
        else
        {
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }
}
