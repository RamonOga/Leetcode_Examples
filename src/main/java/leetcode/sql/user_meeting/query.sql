/*Количество мероприятий по датам*/
select m.date, count(u_m.user_id) from meeting as m join users_meeting as u_m on m.id = u_m.user_id group by m.date;
/*Количество определенного мероприятия по датам*/
select m.date, u.name count(u_m.user_id) from meeting as m join users_meeting as u_m on m.id = u_m.user_id join users as u on u.id = u_m.user_id group by m.date;

/*Выводит все даты с именами и и offer'ами*/
select m.date, u.name, u_m.offer from meeting as m join users_meeting as u_m on m.id = u_m.meeting_id join users as u on u.id = u_m.user_id;

