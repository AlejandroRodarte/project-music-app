USE music_db;

CREATE OR REPLACE VIEW bands_view AS 
	SELECT 
		band_album_count_view.id AS id,
		name,
		image_path,
		origin_country, 
		origin_year, 
		IFNULL(band_album_count_view.album_count, 0) AS album_count, 
		IFNULL(band_song_count_view.song_count, 0) AS song_count,
        created_at,
        updated_at
    FROM 
		band_album_count_view 
    LEFT JOIN 
		band_song_count_view 
    ON 
		band_album_count_view.id = band_song_count_view.id;