USE music_db;

CREATE OR REPLACE VIEW songs_view AS
	SELECT 
		songs.id AS id,
		songs.name AS name,
		songs.track_number as track_number,
		songs.duration as duration,
		songs.created_at as created_at,
		songs.updated_at as updated_at,
		albums.id as album_id,
		albums.name as album_name,
		albums.image_path as album_image_path,
		bands.id as band_id,
		bands.name as band_name,
		bands.image_path as band_image_path
		FROM 
			songs 
		LEFT JOIN 
			albums 
		ON 
			songs.album_id = albums.id 
		LEFT JOIN 
			bands 
		ON 
			albums.band_id = bands.id;