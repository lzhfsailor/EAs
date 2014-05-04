package simplePSO;

import java.util.Random;

/**
 * Copyright &reg; 2014 Jinan
 * 
 * @description v[i] = w * v[i] + c1 * rand() * (pbest[i] - present[i]) + c2 *
 *              rand() * (gbest - present[i]);present[i] = present[i] + v[i]
 * @author Heming.Liang
 * @version 1.0 ����Ⱥ�Ż��㷨���y=-x*(x-2) ��[-2,2]�����ֵ All right reserved.
 */
public class Maximum {

	int n = 2; // ���Ӹ���
	double[] y;// ����λ��
	double[] x;// ����ȡֵ
	double[] v;// ���Ӹ����ٶ�
	double c1 = 2;
	double c2 = 2;
	double pbest[];// ������ʷ���λ��
	double gbest;// ȫ������λ��
	double vmax = 0.1;// �����ٶ����ֵ

	public void fitnessFunction() {// ��Ӧ����
		for (int i = 0; i < n; i++) {
			y[i] = -1 * x[i] * (x[i] - 2);
		}
	}

	public void init() { // ��ʼ��
		x = new double[n];
		v = new double[n];
		y = new double[n];
		pbest = new double[n];
		/***
		 * Math.random()����0-1֮������Ϊdouble�������
		 */
		for (int i = 0; i < n; i++) {
			x[i] = Math.random() * 4 - 2;
			v[i] = x[i] > vmax ? vmax : x[i];
		}
		fitnessFunction();
		// ��ʼ����ǰ���弫ֵ�����ҵ�Ⱥ�弫ֵ
		for (int i = 0; i < n; i++) {
			pbest[i] = y[i];
			if (y[i] > gbest)
				gbest = y[i];
		}
		System.out.println("start gbest:" + gbest);
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
				pbest[j] = y[j] > pbest[j] ? y[j] : pbest[j];
				if (pbest[j] > gbest)
					gbest = pbest[j];
				System.out.println(x[j] + "  " + v[j] + "  " + y[j]);
			}
			System.out.println("======generation " + (i + 1) + "======gbest:"
					+ gbest);
		}

	}

	public static void main(String[] args) {
		Maximum ts = new Maximum();
		ts.init();
		ts.PSO(1000);

	}

}
