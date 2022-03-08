package edu.uoc.pac1;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class PAC1Ex2 {

    static DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("EN"));
    static DecimalFormat decimalFormat = new DecimalFormat("0.00", symbols);

    public static final double RATE_FIRST_YEAR = 1.95;

    private static double power(double base, int exponent) {
        if (exponent > 0) return power(base, exponent - 1) * base;
        else if (exponent < 0) return power(base, exponent + 1) / base;
        else return 1; //Base case
    }

    public static double mortgageMonthlyFee(double capital, double annualInterestRate, int months) {

        double cuota,monthlyInterestRate;

        if (capital <= 0 || annualInterestRate <= 0 || months <= 0) {
            cuota = -1.0;
            return cuota;

        } else {

            monthlyInterestRate = (annualInterestRate / 100) / 12;
            cuota = (capital * monthlyInterestRate) / (1 - (power((monthlyInterestRate + 1), -months)));
            return cuota;

        }
    }

    public static double mortgageAmortizationSchedule(double capital, int years, double annualInterestRate) {

        double annualInteresRateFirstYear, fee,monthlyInteresRate,amortization,interest,debt=capital,
        interestTotal =0,debtFirstYear =0;
        int months=years*12;

        if (annualInterestRate > RATE_FIRST_YEAR) {
            System.out.println("The interest applied must be less than the fixed interest of the first year");
            return -1;

        } else if(years<2) {
            System.out.println("The duration of the mortgage must be equal to or greater than two years");
            return  -1;

        }else{
            for (int i = 0; i < months; i++) {

                if (i < 12) {

                    annualInteresRateFirstYear =RATE_FIRST_YEAR;
                    fee=mortgageMonthlyFee(capital, annualInteresRateFirstYear,months);
                    monthlyInteresRate =(annualInteresRateFirstYear /100/12);
                    interest=debt*monthlyInteresRate;
                    interestTotal += interest;
                    amortization =fee-interest;
                    debt=debt- amortization;
                    debtFirstYear =debt;

                    System.out.println("Month: " + (i + 1) + ", Fee: " + (decimalFormat.format(fee)) + ", Interest: "
                            + (decimalFormat.format(interest)) + ", Amortization: "+(decimalFormat.format(amortization))
                            + ", Debt: "+(decimalFormat.format(debt)));

                } else {

                    fee=mortgageMonthlyFee(debtFirstYear,annualInterestRate,months-12);
                    monthlyInteresRate=(annualInterestRate/100/12);
                    interest=debt*monthlyInteresRate;
                    interestTotal = interestTotal +interest;
                    amortization =fee-interest;
                    debt=debt- amortization;

                    System.out.println("Month: " + (i + 1) + ", Fee: " + (decimalFormat.format(fee)) + ", Interest: "
                            + (decimalFormat.format(interest)) + ", Amortization: "+(decimalFormat.format(amortization))
                            + ", Debt: "+(decimalFormat.format(debt)));

                }

            }
            return interestTotal;
        }
    }



        public static double fixedRate ( double capital){

            double fixed;

            if (capital > 150000) {
                fixed = 1.25;
            } else {
                if (capital <= 100000) {
                    fixed = 1.06;
                } else {
                    fixed = 1.15;
                }
            }
            return fixed;
        }

        public static double mortgageAmortizationSchedule ( double capital, int years) {

            int months = years * 12;
            double debtFirstyear = 0,interestTotal =0,annualInterestRate,monthlyInterestRate,fee,interest,amortization,
                    debt=capital,annualInterestFixedRate=fixedRate(capital);

            if (years < 2) {

                System.out.println("The duration of the mortgage must be equal to or greater than two years");
                return -1;

            } else {

                for (int i = 0; i < months; i++) {

                    if(i<12){

                        annualInterestRate = RATE_FIRST_YEAR;
                        fee = mortgageMonthlyFee(capital, annualInterestRate, months);
                        monthlyInterestRate = (annualInterestRate / 100) / 12;
                        interest = debt*monthlyInterestRate;
                        interestTotal += interest;
                        amortization = fee-interest;
                        debt = debt-amortization;
                        debtFirstyear = debt;

                        System.out.println("Month: " + (i + 1) + ", Fee: " + decimalFormat.format(fee) + ", Interest: "
                                + decimalFormat.format(interest) + ", Amortization: "+decimalFormat.format(amortization)
                                + ", Debt: "+decimalFormat.format(debt));

                    }else{

                        fee = mortgageMonthlyFee(debtFirstyear, annualInterestFixedRate, months - 12);
                        monthlyInterestRate = (annualInterestFixedRate / 100) / 12;
                        interest = debt * monthlyInterestRate;
                        interestTotal +=interest;
                        amortization =fee-interest;
                        debt = debt - amortization;


                        System.out.println("Month: " + (i + 1) + ", Fee: " + decimalFormat.format(fee) + ", Interest: "
                                + decimalFormat.format(interest) + ", Amortization: "+decimalFormat.format(amortization)
                                + ", Debt: "+decimalFormat.format(debt));

                    }

                }

                return interestTotal;
            }
        }
    }
