package com.system.exam.exam.services;

import com.system.exam.exam.entities.Quiz;
import com.system.exam.exam.repositories.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    private QuizRepo quizRepo;

    @Autowired
    public QuizService(QuizRepo quizRepo) {
        this.quizRepo = quizRepo;
    }

    public List<Quiz> getQuizList(){
        return this.quizRepo.findAll();
    }

    public Quiz getQuizById(int id){
        Optional<Quiz> data = this.quizRepo.findById(id);
        return data.isPresent() ? data.get() : null;
    }

    public Quiz createQuiz(Quiz quiz){
        return this.quizRepo.save(quiz);
    }

    public boolean deleteQuizById(int id){
        boolean quizExist = quizRepo.findById(id).isPresent();
        if(quizExist){
            this.quizRepo.deleteById(id);
        }

        return quizExist;
    }

    public Quiz editQuiz(Quiz updatingQuiz){
        return this.quizRepo.save(updatingQuiz);
    }
}
