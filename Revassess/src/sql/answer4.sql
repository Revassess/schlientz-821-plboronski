SELECT username, name, question, answer 
    FROM study_set s INNER JOIN app_user u
        ON s.owner_id = u.user_id 
    INNER JOIN study_set_card c
        ON s.study_set_id = c.study_set_id
    INNER JOIN flashcard f
        ON c.flashcard_id = f.flashcard_id
    WHERE u.user_id = 4;
    