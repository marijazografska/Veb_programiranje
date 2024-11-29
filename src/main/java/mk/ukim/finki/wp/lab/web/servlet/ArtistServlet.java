package mk.ukim.finki.wp.lab.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name="servlet_artist", urlPatterns = "/servlet/artist")
public class ArtistServlet extends HttpServlet {
    private final ArtistService artistService;
    private final SongService songService;
    private final SpringTemplateEngine springTemplateEngine;

    public ArtistServlet(ArtistService artistService, SpringTemplateEngine springTemplateEngine,SongService songService) {
        this.artistService = artistService;
        this.songService=songService;
        this.springTemplateEngine = springTemplateEngine;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String trackId = (String) req.getSession().getAttribute("trackId");
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(webExchange);

        context.setVariable("ipAddress", req.getRemoteAddr());
        context.setVariable("userAgent", req.getHeader("user-agent"));
        context.setVariable("artists", artistService.listArtists());
        context.setVariable("trackId", trackId);


        springTemplateEngine.process("artistsList.html", context, resp.getWriter());
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String artistId = req.getParameter("artistId");
        String trackId = (String) req.getSession().getAttribute("trackId");

        songService.addArtistToSong(artistService.ArtistfindById(artistId),songService.findByTrackId(trackId));

        resp.sendRedirect("/songDetails");  // Redirect back to song list page
    }
}
