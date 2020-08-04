SELECT * FROM app_user u JOIN study_set s 
    ON u.user_id = s.owner_id
    WHERE u.user_id = 5;