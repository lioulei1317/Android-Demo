package cn.com.tarena.coreandroid.d0901.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.audiofx.Visualizer;
import android.media.audiofx.Visualizer.OnDataCaptureListener;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * By CWD 2013 Open Source Project
 * 
 * <br>
 * <b>�Զ�����ӻ�Ч��(Ƶ����ͼ)��ʹ��һ��Ҫ��android.permission.RECORD_AUDIOȨ��</b></br>
 * 
 * ȱ���ǲ��ܹ������춯��һ����������������������С������
 * 
 * @author CWD
 * @version 2013.06.30 v1.0 ʵ�ֿ��ӻ�Ч��<br>
 *          2013.07.23 v1.1 �޸����ֳ������</br>
 */
public class VisualizerView extends View {

	private final int REFRESH_TIME = 100;// ˢ�¼��
	private final int TIMES_MAX = 10;// ˢ��������
	private final int ACCELERATION = 1;// ������ٶ�(�о�����ʵ�һ����ֵ)

	private Visualizer mVisualizer = null;
	private Handler mHandler = null;

	private int mSpectrumNum = 48;// ��ȡһ����
	private int times = 0;// ��¼ˢ�´���

	private boolean error = false;// �Ƿ��ʼ������
	private boolean canDrawLines = false;// �Ƿ�������

	private byte[] mBytes = null;// FFTԴ����
	private float[] linesArray = null;// ������
	private float[] pointsArray = null;// ������
	private float[] tempArray = null;// ��ʱ���飬���ڼ�ס���λ��

	private Rect mRect = null;// ��������
	private Paint linesPaint = null;// Ƶ���߻���
	private Paint pointsPaint = null;// Ƶ�׵㻭��
	private Paint errorPaint = null;// Ƶ�׵㻭��
	int[] colorArray = { 0xFF000000, 0xFF000000, 0xFF444444, 0xFF888888,
			0xFFCCCCCC, 0xFFFFFFFF, 0xFFFF0000, 0xFF00FF00, 0xFF0000FF,
			0xFFFFFF00, 0xFF00FFFF, 0xFFFF00FF};
	int colorPostion=0;

	public VisualizerView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}

	public VisualizerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init();
	}

	public VisualizerView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init();
	}

	/**
	 * ����һ��VisualizerView����ʹ��ƵƵ�׵Ĳ����ܹ���ӳ�� VisualizerView��
	 * 
	 * @param audioSessionId
	 *            MediaPlayer.getAudioSessionId()
	 */
	public void setupVisualizerFx(int audioSessionId) {
		mHandler.removeCallbacks(runnable);
		canDrawLines = true;// ������
		error = false;// ��ʼֵδ����
		times = 0;

		if (mVisualizer != null) {
			mVisualizer.setEnabled(false);
			mVisualizer.release();
			mVisualizer = null;
		}

		// 4.0��ģ�����϶��ᱨ�����뒁�쳣�������ܲ����ֻ�Ҳ�����
		try {
			mVisualizer = new Visualizer(audioSessionId);
			// �����ڱ�����2��λ��
			mVisualizer.setCaptureSize(Visualizer.getCaptureSizeRange()[0]);
			/*
			 * ���������α�ʾ�����Ҳ�������ע��(true/false)��
			 * ��һ����ʾ�Ƿ�ִ��onWaveFormDataCapture���ڶ�����ʾ�Ƿ�ִ��onFftDataCapture
			 */
			mVisualizer.setDataCaptureListener(new OnDataCaptureListener() {

				@Override
				public void onWaveFormDataCapture(Visualizer visualizer,
						byte[] waveform, int samplingRate) {
					// TODO Auto-generated method stub
					//updateVisualizer(waveform);
				}

				@Override
				public void onFftDataCapture(Visualizer visualizer, byte[] fft,
						int samplingRate) {
					// TODO Auto-generated method stub
					updateVisualizer(fft);
				}
			}, Visualizer.getMaxCaptureRate() / 2, false, true);
			mVisualizer.setEnabled(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			errorPaint = new Paint();
			errorPaint.setColor(Color.argb(230, 255, 255, 255));
			errorPaint.setTextSize(20);
			error = true;
			e.printStackTrace();
		}
	}

	/**
	 * �ͷŲ�����VisualizerView����
	 */
	public void releaseVisualizerFx() {
		if (mVisualizer == null) {
			return;
		}
		canDrawLines = false;// ֹͣ����
		mVisualizer.setEnabled(false);
		mHandler.postDelayed(runnable, REFRESH_TIME);
	}

	// ��ʼ������
	private void init() {
		mRect = new Rect();
		mHandler = new Handler();

		linesPaint = new Paint();
		linesPaint.setStrokeWidth(5f);
		linesPaint.setAntiAlias(true);
		linesPaint.setColor(Color.argb(230, 134, 221, 140));

		pointsPaint = new Paint();
		pointsPaint.setStrokeWidth(5f);
		pointsPaint.setAntiAlias(true);

		pointsPaint.setColor(Color.argb(180, 218, 112, 214));
		int[] a = { 0, 1, 2 };

		initByte();
	}

	/**
	 * ��ʼ��FFTԴ���飬��ֵΪ1��Ŀ�����ܹ�һ��ʼ�ͻ���Ƶ�ף���ʼ����Ŀ�ؼ����¹�λ
	 */
	private void initByte() {
		mBytes = new byte[mSpectrumNum];
		for (int i = 0; i < mSpectrumNum; i++) {
			mBytes[i] = 0;
		}
	}

	private void updateVisualizer(byte[] mbyte) {

		byte[] model = new byte[mbyte.length / 2 + 1];
//Log.i("mbyte", mbyte[0]+"");
		model[0] = (byte) Math.abs(mbyte[0]);
//Log.i("mbyte", model[0]+"");
		for (int i = 2, j = 1; j < mSpectrumNum;) {
			//����ֵΪsqrt(x2 +y2);
			model[j] = (byte) Math.hypot(mbyte[i], mbyte[i + 1]);
			
			i += 2;
			j++;
		}

		mBytes = model;
		invalidate();
	}

	/**
	 * ������������{h=(1/2)*g*t^2}
	 * 
	 * @param time
	 *            ʱ��
	 * @return �˶��ľ���
	 */
	private float freeFall(float time) {
		float h = ACCELERATION * time * time / 2;
		return h;
	}

	private Runnable runnable = new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			invalidate();
			if (times < TIMES_MAX) {
				if (times == 1) {
					initByte();// ����һ�Σ�����onFftDataCapture���ٻص�һ�Σ���ʼ����û������
				}
				mHandler.postDelayed(this, REFRESH_TIME);
			}
			times++;
		}
	};

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);

		if (mBytes == null) {
			return;
		}

		if (error) {
			String s = "���ӻ�Ч����ʼ��ʧ��";
			canvas.drawText(s, getWidth() / 2 - errorPaint.measureText(s) / 2,
					getHeight() / 2, errorPaint);
			return;
		}

		int length = mSpectrumNum * 4;
		if (linesArray == null || linesArray.length < length) {
			linesArray = new float[length];
		}
		length = mSpectrumNum * 2;
		if (pointsArray == null || pointsArray.length < length) {
			pointsArray = new float[length];
		}
		length = mSpectrumNum * 3;
		if (tempArray == null || tempArray.length < length) {
			tempArray = new float[length];
			for (int l = 0; l < length; l++) {
				if (l % 3 == 0) {
					continue;// ����ָ����3�ı�����ֵ����Ϊ0
				}
				tempArray[l] = 1024;// ����һ���㹻��ĳ�ʼֵ(Ӧ�ù����˰�)������һ��ʼ��������������
			}
		}

		mRect.set(0, 0, getWidth(), getHeight());

		/************ ���Ʋ��� ************/
		// for (int i = 0; i < mBytes.length - 1; i++) {
		// linesArray[i * 4] = mRect.width() * i / (mBytes.length - 1);
		// linesArray[i * 4 + 1] = mRect.height() / 2
		// + ((byte) (mBytes[i] + 128)) * (mRect.height() / 2) / 128;
		// linesArray[i * 4 + 2] = mRect.width() * (i + 1) / (mBytes.length -
		// 1);
		// linesArray[i * 4 + 3] = mRect.height() / 2
		// + ((byte) (mBytes[i + 1] + 128)) * (mRect.height() / 2)
		// / 128;
		// }

		/************ ������״Ƶ�� ************/
		final int baseX = mRect.width() / mSpectrumNum;
		final int height = mRect.height();

		for (int i = 0; i < mSpectrumNum; i++) {
			if (mBytes[i] < 0) {
				mBytes[i] = 127;
			}

			final int x = baseX * i + baseX / 2;

			if (canDrawLines) {
				// ���㹹��һ��ֱ��(��ֱ)
				linesArray[i * 4] = x;// ��ʼ��X����
				linesArray[i * 4 + 1] = height;// ��ʼ��Y����
				linesArray[i * 4 + 2] = x;// �յ�X����
				linesArray[i * 4 + 3] = height - mBytes[i];// �յ�Y����
				if(colorPostion>=11)colorPostion=0;
				linesPaint.setColor(colorArray[colorPostion]);
				colorPostion++;
				//Log.i("height", height+"");
				//Log.i("height-2", height - mBytes[i]+"");
				canvas.drawLine(x, height, x, height - 3*mBytes[i], linesPaint);
				
			}

			// ���յ�λ�õĸ߶ȼ�ȥһ�����ͻ�������ߵ��Ϸ�����֮���·�
			int y = height - 3*mBytes[i] - 3;
			if (tempArray[i * 3 + 1] > y) {// ��ס��λ�ñ����ڵĴ�Ҳ�������µ�λ�ø�����
				tempArray[i * 3] = x;
				tempArray[i * 3 + 1] = y;// ��ס���ڵ����λ��
				tempArray[i * 3 + 2] = 0;// һ��Ҫ��0
				pointsArray[i * 2] = x;
				pointsArray[i * 2 + 1] = y;// �����˾�ˢ����
			} else {// ��ס��λ�ñ����ڵ�С��˵��������������
				tempArray[i * 3] = x;
				float ti = tempArray[i * 3 + 2];// ȡ���ϴμ���Ĵ���
				float temp = tempArray[i * 3 + 1] + freeFall(ti);// ����������������
				tempArray[i * 3 + 2] = ++ti;// ����һ��
				temp = temp > y ? y : temp;// �����ܵ����µ�ס�ˣ��ж�һ���õ��λ
				tempArray[i * 3 + 1] = temp;
				pointsArray[i * 2] = x;
				pointsArray[i * 2 + 1] = tempArray[i * 3 + 1];// ˢ�µ�ǰ��
			}
		}

//		if (canDrawLines) {
//			if(colorPostion>=11)colorPostion=0;
//			linesPaint.setColor(colorArray[colorPostion]);
//			colorPostion++;
//			
//			canvas.drawLines(linesArray, linesPaint);
//		}
		pointsPaint.setColor(colorArray[colorPostion]);
		canvas.drawPoints(pointsArray, pointsPaint);
	}

	@Override
	protected void onDetachedFromWindow() {
		// TODO Auto-generated method stub
		try {
			mHandler.removeCallbacks(runnable);// ȷ���Ƴ��߳�
			super.onDetachedFromWindow();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
