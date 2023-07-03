package it.unicam.cs.ids.loyaltyPlatform;

import it.unicam.cs.ids.loyaltyPlatform.PendingAnswers.UnionProgramPendingAnswersRepository;
import it.unicam.cs.ids.loyaltyPlatform.PendingAnswers.UnionProgramPendingAnswersService;
import it.unicam.cs.ids.loyaltyPlatform.Question.QuestionRepository;
import it.unicam.cs.ids.loyaltyPlatform.Question.QuestionService;
import it.unicam.cs.ids.loyaltyPlatform.UnionProgram.UnionProgramRepository;
import it.unicam.cs.ids.loyaltyPlatform.UnionProgram.UnionProgramService;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.FidelityProgramRepository;
import it.unicam.cs.ids.loyaltyPlatform.loyaltyProgram.FidelityProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
@Configuration
public class AppConfig {
    @Autowired
    private UnionProgramService unionProgramService;

    @Autowired
    private UnionProgramRepository unionProgramRepository;

    @Autowired
    private FidelityProgramService fidelityProgramService;

    @Autowired
    private FidelityProgramRepository fidelityProgramRepository;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UnionProgramPendingAnswersService unionProgramPendingAnswersService;

    @Autowired
    private UnionProgramPendingAnswersRepository unionProgramPendingAnswersRepository;

    @Bean
    public UnionProgramService unionProgramService() {
        return new UnionProgramService(unionProgramRepository, fidelityProgramService, ownerService);
    }

    @Bean
    public QuestionService questionService() {
        return new QuestionService(questionRepository, unionProgramPendingAnswersService);
    }

    @Bean
    public UnionProgramPendingAnswersService unionProgramPendingAnswersService() {
        return new UnionProgramPendingAnswersService(unionProgramPendingAnswersRepository(), unionProgramService());
    }

    @Bean
    public UnionProgramPendingAnswersRepository unionProgramPendingAnswersRepository() {
        return new UnionProgramPendingAnswersRepository(unionProgramPendingAnswersRepository);
    }
}

 */