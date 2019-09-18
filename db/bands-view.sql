CREATE OR REPLACE VIEW bands_view AS
	SELECT 
		bands_count_view.id AS id,
        name,
        image_path,
        origin_country,
        origin_year,
        album_count,
        song_count,
        created_at,
        updated_at,
        genre,
        artist_first_name,
        artist_last_name
		FROM
			bands_count_view
		LEFT JOIN
			bands_artist_genre_view
		ON
			bands_count_view.id = bands_artist_genre_view.id;