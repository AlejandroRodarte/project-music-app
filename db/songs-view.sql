USE music_db;

CREATE OR REPLACE VIEW songs_view AS
	SELECT 
		songs.id AS id,
		songs.name AS name,
		songs.track_number AS track_number,
		songs.duration AS duration,
		songs.created_at AS created_at,
		songs.updated_at AS updated_at,
		albums.id AS album_id,
		albums.name AS album_name,
		albums.image_path AS album_image_path,
		bands.id AS band_id,
		bands.name AS band_name,
		bands.image_path AS band_image_path
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