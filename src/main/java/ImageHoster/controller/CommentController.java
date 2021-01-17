package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.repository.CommentRepository;
import ImageHoster.repository.ImageRepository;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    //http://localhost:8080/image/15/B%20uploaded%20-%20add%20new/comments
    //This controller method will be called when any user will add any comments to any image. Comments are open for all logged in users.
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(@PathVariable(name = "imageId") Integer imageId,
                                @PathVariable(name="imageTitle") String imageTitle,
                                @RequestParam(name="comment") String comment,
                                HttpSession session, Model model) {
        User user = (User) session.getAttribute("loggeduser");
        System.out.println("user id : "+user.getId()+" "+user.getUsername());
        Comment newComment = new Comment();
        newComment.setText(comment);
        newComment.setCreatedDate(new Date());
        newComment.setUser(user);
        newComment.setImage(imageService.getImage(imageId));
        commentService.createComment(newComment);

        Image image = imageService.getImage(imageId);
        List<Comment> comments = image.getComments();
        comments.add(newComment);
        image.setComments(comments);
        imageService.updateImage(image);

        model.addAttribute("image", image);
        model.addAttribute("tags", image.getTags());
        model.addAttribute("comments", image.getComments());
        System.out.println("****************Image coments : "+image.getComments());
        return "redirect:/images/"+imageId+"/"+imageTitle;
    }

}
