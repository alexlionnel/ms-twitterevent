package io.demobrains.twitterevent.elastic.query.webclient.api;

import io.demobrains.twitterevent.elastic.query.webclient.common.model.ElasticQueryWebClientAnalyticsResponseModel;
import io.demobrains.twitterevent.elastic.query.webclient.common.model.ElasticQueryWebClientRequestModel;
import io.demobrains.twitterevent.elastic.query.webclient.common.model.ElasticQueryWebClientResponseModel;
import io.demobrains.twitterevent.elastic.query.webclient.service.ElasticQueryWebClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class QueryController {

    private final ElasticQueryWebClient elasticQueryWebClient;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("elasticQueryWebClientRequestModel", ElasticQueryWebClientRequestModel.builder().build());
        return "home";
    }

    @PostMapping("/query-by-text")
    public String queryByText(@Valid ElasticQueryWebClientRequestModel requestModel, Model model) {
        log.info("Querying with text {}", requestModel.getText());
        var responseModels = elasticQueryWebClient.getDataByText(requestModel);
        model.addAttribute("elasticQueryWebClientResponseModels", responseModels.getQueryResponseModels());
        model.addAttribute("wordCount", responseModels.getWordCount());
        model.addAttribute("searchText", requestModel.getText());
        model.addAttribute("elasticQueryWebClientRequestModel", requestModel);
        return "home";
    }
}

