package ssm.newscms.util;

import org.apache.commons.lang.StringUtils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class CryptoUtils {

	public CryptoUtils() {

	}

	private static final String CRYPTO_MD5 = "MD5";

	public static String messageDigest(final String password, final String type) {
		StringBuffer retval = new StringBuffer();
		try {
			final byte[] passBytes = password
					.getBytes(Charset.defaultCharset());
			final byte[] encodeBytes;

			if (StringUtils.isEmpty(type)) {
				encodeBytes = MessageDigest.getInstance(CRYPTO_MD5).digest(
						passBytes);
			} else {
				encodeBytes = MessageDigest.getInstance(type).digest(passBytes);
			}

			for (int i = 0; i < encodeBytes.length; i++) {
				retval.append(Integer
						.toHexString(((int) encodeBytes[i]) & 0xFF));
			}
		} catch (NoSuchAlgorithmException e) {
			retval = new StringBuffer("");
		}
		return retval.toString();
	}

	/**
	 * 做MD5信息摘要，在生成byte是会进行填充。小于10时前面加"0"
	 * 
	 * @param password
	 * @return
	 * @author odpsoft
	 * @time 2009-1-5
	 */
	public static String messageDigestPassword(String password) {
		StringBuffer retval = new StringBuffer();
		try {
			final byte[] passBytes = password
					.getBytes(Charset.defaultCharset());
			final byte[] encodeBytes;

			encodeBytes = MessageDigest.getInstance(CRYPTO_MD5).digest(
					passBytes);
			String stmp = "";
			for (int i = 0; i < encodeBytes.length; i++) {
				stmp = Integer.toHexString(((int) encodeBytes[i]) & 0xFF);

				if (stmp.length() == 1) {
					stmp = "0" + stmp;
				}

				retval.append(stmp);
			}
		} catch (NoSuchAlgorithmException e) {
			retval = new StringBuffer("");
		}
		return retval.toString();
	}

	/**
	 * 将15位身份证号自动转换为18位身份证号
	 */
	public static String idCard15to18(String identifyCodePar) {
		String identifyCode = identifyCodePar;
		if (identifyCode.length() == 15) // 将15位身份证号自动转换为18位身份证号
		{
			String idCard = identifyCode.trim();
			StringBuffer idCard18 = new StringBuffer(idCard);
			// 加权因子
			// int[] weight = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};
			// 校验码值
			char[] checkBit = { '1', '0', 'X', '9', '8', '7', '6', '5', '4',
					'3', '2' };
			int sum = 0;
			// 15位的身份证
			if (idCard != null && idCard.length() == 15) {
				idCard18.insert(6, "19");
				for (int index = 0; index < idCard18.length(); index++) {
					char c = idCard18.charAt(index);
					int ai = Integer.parseInt(new Character(c).toString());
					// sum = sum+ai*weight[index];
					// 加权因子的算法
					int wi = ((int) Math.pow(2, idCard18.length() - index)) % 11;
					sum = sum + ai * wi;
				}
				int indexOfCheckBit = sum % 11; // 取模
				idCard18.append(checkBit[indexOfCheckBit]);
				identifyCode = idCard18.toString();
			}
		}
		if ("x".equals(identifyCode.substring(17, 18))) {
			identifyCode = identifyCode.substring(0, 17) + "X";
		}
		return identifyCode;
	}

	public static void main(String[] args) {
		System.out.println(CryptoUtils.messageDigestPassword("test"));
	}

}
