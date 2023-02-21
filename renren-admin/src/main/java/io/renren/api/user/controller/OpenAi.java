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
                .prompt("今天天气怎么样")
                .model("text-davinci-002")
                .maxTokens(1000)
                .temperature(0.2)
                .build();
       //service.createCompletion(completionRequest).getChoices().forEach(System.out::println);
        List<CompletionChoice> completionChoices = service.createCompletion(completionRequest).getChoices();
        for (CompletionChoice completionChoice : completionChoices) {

            System.out.println(completionChoice.getText());
        }
    }

}
