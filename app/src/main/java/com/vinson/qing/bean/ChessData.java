package com.vinson.qing.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

/**
 * Created by Vinson on 2017/4/16.
 * e-mail: wei2006004@foxmail.com
 */
@Entity
public class ChessData implements Parcelable {

    @Id(autoincrement = true)
    public Long id;

    public Date startTime;

    public Date endTime;

    public String redPlayer;

    public String greenPlayer;

    @ToMany(referencedJoinProperty = "dataId")
    public List<ChessTrack> tracks;

    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /**
     * Used for active entity operations.
     */
    @Generated(hash = 28950249)
    private transient ChessDataDao myDao;

    @Generated(hash = 115894785)
    public ChessData(Long id, Date startTime, Date endTime, String redPlayer, String greenPlayer) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.redPlayer = redPlayer;
        this.greenPlayer = greenPlayer;
    }

    @Generated(hash = 2145433533)
    public ChessData() {
    }

    protected ChessData(Parcel in) {
        id = in.readLong();
        startTime = (Date) in.readSerializable();
        endTime = (Date) in.readSerializable();
        redPlayer = in.readString();
        greenPlayer = in.readString();
        tracks = in.createTypedArrayList(ChessTrack.CREATOR);
    }

    public static final Creator<ChessData> CREATOR = new Creator<ChessData>() {
        @Override
        public ChessData createFromParcel(Parcel in) {
            return new ChessData(in);
        }

        @Override
        public ChessData[] newArray(int size) {
            return new ChessData[size];
        }
    };

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRedPlayer() {
        return this.redPlayer;
    }

    public void setRedPlayer(String redPlayer) {
        this.redPlayer = redPlayer;
    }

    public String getGreenPlayer() {
        return this.greenPlayer;
    }

    public void setGreenPlayer(String greenPlayer) {
        this.greenPlayer = greenPlayer;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1558884702)
    public List<ChessTrack> getTracks() {
        if (tracks == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ChessTrackDao targetDao = daoSession.getChessTrackDao();
            List<ChessTrack> tracksNew = targetDao._queryChessData_Tracks(id);
            synchronized (this) {
                if (tracks == null) {
                    tracks = tracksNew;
                }
            }
        }
        return tracks;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 1878244390)
    public synchronized void resetTracks() {
        tracks = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeSerializable(startTime);
        dest.writeSerializable(endTime);
        dest.writeString(redPlayer);
        dest.writeString(greenPlayer);
        dest.writeTypedList(tracks);
    }

    private transient int currentIndex = 0;

    public void addTrack(Chess chess, int fromx, int fromy, int tox, int toy) {
        if (tracks == null) {
            tracks = new ArrayList<>();
        }
        tracks.add(new ChessTrack(null, 0, fromx, fromy, tox, toy, currentIndex, chess));
        currentIndex++;
    }

    public List<ChessTrack> getTempTracks() {
        if (tracks == null) {
            return new ArrayList<>();
        }
        return tracks;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 958399222)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getChessDataDao() : null;
    }
}
