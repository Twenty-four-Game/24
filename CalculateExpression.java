
import java.util.StringTokenizer;
import java.util.Arrays;

public class CalculateExpression {
	private String expression;
	private boolean isCorrect;
	private double result;

/** setExpression(String<表达式>),输入表达式 */
	public void setExpression(String input) {
		expression=input;
		isCorrect=check();
		//JOptionPane.showMessageDialog(null,expression,"Test2",JOptionPane.INFORMATION_MESSAGE);
		if (isCorrect) {
			result=calStep1();
		} else
			result=0;
	}

/** getExpression(),输出表达式 */
	public String getExpression() {
		return expression;
	}

/*判断表达式规范与否,并自动修正 
 1，左括号与右括号个数相同
2，两个运算符不可以相邻
3，两个数字不可以相邻
4，运算符的左边不可以是左括号，右边不可以是右括号。
5，右括号不可以在第一个位置，左括号不可以在最后一个位置。
6，一对括号中间不能为空且必须为合法的表达式*/
	private boolean check() {
		char t,tt=expression.charAt(0);
		int sum=0,add=0;		//计算左右括号,计算修正位数
		boolean hasNum=false;
		StringBuffer s=new StringBuffer(expression);
		if (tt=='+' || tt=='-' || tt=='.') {
			s.insert(0,'0');
			add++;
		} else
		if (tt==')' || tt=='*' || tt=='/') {
			return false;
		}
		for (int i=0; i<expression.length(); i++) {
			t=tt;
			if (i!=expression.length()-1) {
				tt=expression.charAt(i+1);
			} else tt='E';
			if (t>='0' && t<='9') {
				hasNum=true;
				if (tt=='(') {
					s.insert(i+add+1,'*');
					add++;
				}
			} else
			if (t=='(') {
				sum++;
				if (tt=='.' || tt=='+' || tt=='-') {
					s.insert(i+add+1,'0');
					add++;
				} else
				if (tt=='*' || tt=='/' || tt==')') {
					return false;
				}
			} else
			if (t==')') {
				sum--;
				if (tt>='0' && tt<='9') {
					s.insert(i+add+1,'*');
					add++;
				} else
				if (tt=='.') {
					return false;
				}
			} else
			if (t=='+') {
				if (tt=='+' || tt=='-') {
					s.deleteCharAt(i+add);
					add--;
				} else
				if (tt=='.') {
					s.insert(i+add+1,'0');
					add++;
				} else
				if (tt=='*' || tt=='/' || tt==')') {
					return false;
				}
			} else
			if (t=='-') {
				if (tt=='+') {
					s.replace(i+add,i+add+2,"-");
					add--;
				} else
				if (tt=='-') {
					s.replace(i+add,i+add+2,"+");
					add--;
				} else
				if (tt=='.') {
					s.insert(i+add+1,'0');
					add++;
				} else
				if (tt=='*' || tt=='/' || tt==')') {
					return false;
				}
			} else
			if (t=='*' || t=='/') {
				if (tt==')' || tt=='+' || tt=='-' || tt=='*' || tt=='/') {
					return false;
				}
			} else
			if (t=='.') {
				if (tt=='.') {
					return false;
				} else
				if (tt<'0' || tt>'9') {
					s.insert(i+add+1,'0');
					add++;
				}
			} else return false;
		} 
		if (sum==0 && hasNum && (expression.indexOf('(')<=expression.indexOf(')'))) {
			expression=s.substring(0);
			return true;
		} else
		return false;
	}

/** isCorrect(),返回表达式是否规范 */
	public boolean isCorrect() {
		return isCorrect;
	}

/** isCorrect(int<数字个数>),返回表达式是否规范 */
	public boolean isCorrect(int n) {
		if (isCorrect&&this.getNumbers().length==n) {
			return true;
		}
		return false;
	}

/** getNumbers(),返回表达式中数字 */
	public int[] getNumbers() {
		if (isCorrect) {
			StringTokenizer t=new StringTokenizer(expression,"+-*/()");
			int[] n=new int[t.countTokens()];
			for (int i=0; i<n.length; i++) {	//若再使用t.countTokens()会有问题,原因不太了解
				n[i]=Integer.parseInt(t.nextToken());
			}
			return n;
		}
		return new int[]{0};
	}

/** getDoubleNumbers(),返回表达式中数字 */
	public double[] getDoubleNumbers() {
		if (isCorrect) {
			StringTokenizer t=new StringTokenizer(expression,"+-*/()");
			double[] n=new double[t.countTokens()];
			for (int i=0; i<n.length; i++) {	//若再使用t.countTokens()会有问题,原因不太了解
				n[i]=Double.parseDouble(t.nextToken());
			}
			return n;
		}
		return new double[]{0};
	}

/** getResult(),计算表达式的结果,返回int类型 */
	public int getResult() {
		if (isCorrect) {
			return (int)result;
		}
		return 0;
	}

/** getDoubleResult(),计算表达式的结果,返回double类型 */
	public double getDoubleResult() {
		if (isCorrect) {
			return result;
		}
		return 0;
	}

/** calStep1(),计算表达式的第一步,剥除括号 */
	private double calStep1() {
		int startIndex,endIndex;
		StringBuffer s=new StringBuffer(expression);
		while (s.substring(0).indexOf('(')!=-1) {
			endIndex=s.substring(0).indexOf(')');
			startIndex=s.substring(0).lastIndexOf('(',endIndex);
			s.replace(startIndex,endIndex+1,""+calStep2(s.substring(startIndex+1,endIndex)));
			if (!isCorrect) {
				return 0;
			}
		}
		return calStep2(s.substring(0));
	}

/** calStep2(),计算表达式的第二步,先乘除后加减 */
	private double calStep2(String expression2) {
		boolean isOdd=true;
		StringTokenizer s=new StringTokenizer(expression2,"+-",true);
		expression2="";
		while (s.hasMoreTokens()) {
			if (isOdd) {
				expression2+=(""+calStep3(s.nextToken()));
				isOdd=false;
			}
			else {
				expression2+=s.nextToken();
				isOdd=true;
			}
			if (!isCorrect) {
				return 0;
			}
		}
		return calStep3(expression2);
	}

/** calStep3(),计算表达式的第三步,也就是最后一步,计算加减乘除 */
	private double calStep3(String expression2) {
		StringTokenizer s=new StringTokenizer(expression2,"+-*/",true);
		double n=Double.parseDouble(s.nextToken());
		double nTmp;
		char operator;
		while (s.hasMoreTokens()) {
			operator=s.nextToken().charAt(0);
			nTmp=Double.parseDouble(s.nextToken());
			switch (operator) {
				case '+' :
					n+=nTmp;break;
				case '-' :
					n-=nTmp;break;
				case '*' :
					n*=nTmp;break;
				default :
					if (nTmp==0) {
						isCorrect=false;
						return 0;
					} else
						n/=nTmp;
			}
		}
		return n;
	}

/** compareNumbers(int[]<需对比的数组>),比较表达式中的数字是否与给出的数相同 */
	public boolean compareNumbers(int[] n) {
		if (isCorrect) {
			int[] m=getNumbers();
			if (m.length==n.length) {
				java.util.Arrays.sort(n);
				java.util.Arrays.sort(m);
				for (int i=0; i<n.length; i++) {
					if (n[i]!=m[i]) {
						return false;
					}
				}
				return true;
			} else
				return false;
		}
		return false;
	}
}
