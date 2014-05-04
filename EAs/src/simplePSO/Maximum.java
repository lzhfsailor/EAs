package simplePSO;

/**
 * Copyright &reg; 2014 Jinan.
 * 
 * @author Heming.Liang
 * @version 1.0 ����Ⱥ�Ż��㷨���y=-x*(x-1) ��[-2,2]�����ֵ All right reserved.
 */
public class Maximum {

	int n = 2; // ���Ӹ���������Ϊ�˷�����ʾ������ֻȡ�������۲����˶�����
	double[] y;
	double[] x;
	double[] v;
	double c1 = 2;
	double c2 = 2;
	double pbest[];
	double gbest;
	double vmax = 0.1;

	public void fitnessFunction() {// ��Ӧ����
		for (int i = 0; i < n; i++) {
			y[i] = -1 * x[i] * (x[i] - 1);
		}
	}

	public void init() { // ��ʼ��
		x = new double[n];
		v = new double[n];
		y = new double[n];
		pbest = new double[n];
		/***
		 * ������Ӧ����������ģ�Ϊ�˷�����ʾ���������ֶ�����������㣬�ֱ��������ֵ����
		 */
		x[0] = -0.5;
		x[1] = 2.6;
		v[0] = 0.01;
		v[1] = 0.02;
		fitnessFunction();
		// ��ʼ����ǰ���弫ֵ�����ҵ�Ⱥ�弫ֵ
		for (int i = 0; i < n; i++) {
			pbest[i] = y[i];
			if (y[i] > gbest)
				gbest = y[i];
		}
		System.out.println("start gbest:" + gbest);
	}

	public double getMAX(double a, double b) {
		return a > b ? a : b;
	}

	public void PSO(int max) {
		for (int i = 0; i < max; i++) {
			double w = 0.4;
			for (int j = 0; j < n; j++) {
				// ����λ�ú��ٶ�
				v[j] = w * v[j] + c1 * Math.random() * (pbest[j] - x[j]) + c2
						* Math.random() * (gbest - x[j]);
				if (v[j] > vmax)
					v[j] = vmax;
				x[j] += v[j];
				// Խ���ж�
				if (x[j] > 2)
					x[j] = 2;
				if (x[j] < -2)
					x[j] = -2;

			}
			fitnessFunction();
			// ���¸��弫ֵ��Ⱥ�弫ֵ
			for (int j = 0; j < n; j++) {
				pbest[j] = getMAX(y[j], pbest[j]);
				if (pbest[j] > gbest)
					gbest = pbest[j];
				System.out.println(x[j] + "  " + v[j]);
			}
			System.out.println("======" + (i + 1) + "======gbest:" + gbest);
		}

	}

	public static void main(String[] args) {
		Maximum ts = new Maximum();
		ts.init();
		ts.PSO(100);

	}

}
