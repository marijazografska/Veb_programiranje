package mk.ukim.finki.wp.lab.service.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.thymeleaf.context.WebContext;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public class CustomHandler {

    public static void handleError(HttpServletRequest request, WebContext context)
    {
        String error = request.getParameter("error");
        context.setVariable("error", error);
    }

    public static<T,E> boolean filterHandle(Optional<T> value, E object, Predicate<E> predicate)
    {
        if (value.isEmpty())
        {
            return true;
        }

        return predicate.test(object);
    }

    public static<E> Optional<E> createOptional(E value)
    {
        if (value == null) return Optional.empty();
        if (value instanceof String && ((String) value).isEmpty()) return Optional.empty();

        return Optional.of(value);
    }

    public static boolean isNullOrEmpty(String s)
    {
        return s == null || s.isEmpty();
    }

    public static long getRandomId()
    {
        return (long) (Math.random() * 1000);
    }

    public static String sendRedirect(String path, String error)
    {
        if (!CustomHandler.isNullOrEmpty(error))
        {
            String errorMessage = URLEncoder.encode(error, StandardCharsets.UTF_8);
            return String.format("redirect:%s?error=%s",path, error);
        }
        else
        {
            return String.format("redirect:%s",path);
        }
    }

    public static boolean anyNullOrEmpty(String ... params)
    {
        return Arrays.stream(params)
                .anyMatch(CustomHandler::isNullOrEmpty);
    }
}
