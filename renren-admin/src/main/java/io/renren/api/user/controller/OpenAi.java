package io.renren.api.user.controller;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;

import java.util.List;

/**
 * @auth kolt.yu
 * @时间： 2023/2/21 12:39 PM
 */
public class OpenAi {
    public static void main(String[] args){
        OpenAiService service = new OpenAiService("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");
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt("用中文作一首诗")
                .model("text-davinci-002")
                .maxTokens(1000)
                .temperature(0.2)
                .build();
        List<CompletionChoice> completionChoices = service.createCompletion(completionRequest).getChoices();
        for (CompletionChoice completionChoice : completionChoices) {

            System.out.println(completionChoice.getText());
        }
    }





}
