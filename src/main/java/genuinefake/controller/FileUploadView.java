package genuinefake.controller;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import logic.StringsProcessor;
import org.primefaces.event.FileUploadEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.*;
import java.util.*;


/**
 * Created by fnord on 17.5.28.
 */
@ManagedBean
@SessionScoped
public class FileUploadView {
    private static List<String> uploadedStrings = new ArrayList<>();
    private StringsProcessor stringsProcessor = new StringsProcessor();
    private Map<String, Integer> wordMap;


    private void save(String s) {
        uploadedStrings.add(s);
    }

    /**
     * Converts stream to string.
     *
     * @param event FileUploadEvent
     */
    public void handleFileUpload(FileUploadEvent event) {
        String result = "";
        try {
            result = CharStreams.toString(new InputStreamReader(
                    event.getFile().getInputstream(), Charsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
            FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "The files were not uploaded!", "");
            FacesContext.getCurrentInstance().addMessage(null, error);
        }
        FacesMessage message = new FacesMessage("Succesful", "Files were uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);

        save(result);
    }

    public String giveResults() {
        wordMap = stringsProcessor.process(uploadedStrings);
        return "result.xhtml?faces-redirect=true";
    }

    public List<String> getUploadedStrings() {
        return uploadedStrings;
    }

    public Map<String, Integer> getWordMap() {
        return wordMap;
    }

    public void setWordMap(Map<String, Integer> wordMap) {
        this.wordMap = wordMap;
    }
}



