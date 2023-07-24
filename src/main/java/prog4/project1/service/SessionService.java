package prog4.project1.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import prog4.project1.repository.SessionRepository;
import prog4.project1.repository.entity.Session;

@Service
@AllArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    public Session getSessionById(Integer id){
        return sessionRepository.getById(id);
    }

    public Session addSession(Session session){
       return sessionRepository.save(session);
    }
}
