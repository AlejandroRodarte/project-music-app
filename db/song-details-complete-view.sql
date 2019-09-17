USE music_db;

CREATE OR REPLACE VIEW song_details_view AS
	SELECT 
		song_details.id AS id,
		song_details.lyrics AS lyrics,
		song_details.youtube_url AS youtube_url,
		song_details.created_at AS created_at,
		song_details.updated_at AS updated_at,
		songs.id AS song_id,
		songs.name AS name,
		songs.track_number AS track_number,
		songs.duration AS duration,
		albums.id AS album_id,
		albums.name AS album_name,
		albums.image_path AS album_image_path,
		bands.id AS band_id,
		bands.name AS band_name,
		bands.image_path AS band_image_path
		FROM
			song_details
		INNER JOIN
			songs
		ON
			song_details.song_id = songs.id
		INNER JOIN
			albums
		ON
			songs.album_id = albums.id
		INNER JOIN
			bands
		ON
			albums.band_id = bands.id;