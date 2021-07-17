package uz.pdp.appwarehouse.controllar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.appwarehouse.entity.Attachment;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.AttechmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/attechment")
public class AttachmentController {

    @Autowired
    AttechmentService attechmentService;



    @PostMapping(value = "/upload")
    public Result uploadFile(MultipartHttpServletRequest request) throws IOException {
        return attechmentService.uploadAttachment(request);

    }


    @GetMapping(value = "/info")
    public List<Attachment> getInfo(){
        return attechmentService.getAttachment();
    }

    @GetMapping(value = "/info{id}")
    public Attachment getAttachmentInfo(@PathVariable Integer id){

        return attechmentService.getAttachmentById(id);
    }

    @GetMapping(value = "/download/{id}")
    public void download(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        attechmentService.downloadAttachment(id, response);
    }
}
