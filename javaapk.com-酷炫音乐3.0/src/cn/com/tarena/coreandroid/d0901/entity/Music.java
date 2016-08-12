package cn.com.tarena.coreandroid.d0901.entity;

/**
 * 歌曲的实体类
 * @author tarena
 *
 */
public class Music {
	private long id;
	private String data;
	private String title;
	private long duration;
	private String artist;
	private String album;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	@Override
	public String toString() {
		return "Music [id=" + id + ", data=" + data + ", title=" + title
				+ ", duration=" + duration + ", artist=" + artist + ", album="
				+ album + "]";
	}
	
}
