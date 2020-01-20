package com.schedule.schedule.logic;

import com.schedule.schedule.Entity.IsOccupiedBy;
import com.schedule.schedule.Entity.Wish;
import com.schedule.schedule.service.IsOccupiedByService;
import com.schedule.schedule.service.WishService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class AiLogicImpl implements AiLogic {


    /* LOGICA IMPLEMENTARII (PARCURGERE PE ZILE)

       PARCURGEM INTREG ORAR-UL PE FIECARE ZI IN PARTE, PANA CAND LISTA DE DORINTE TOTALA ESTE EGALA CU LISTA DE DORINTE PE CARE O ACTUALIZAM CONSTANT IN FOR SUNT EGALE CA SI LUNGIME

       PE FIECARE ZI VOM CATEVA DORINTE ALE TUTUROR PROFESORILOR, VOM FACE O PARCURGERE A LOR, CAUTAND IN PRIMA FAZA PROFESORII CARE DORESC SA OCUPE ACEIASI SALA, PE ACELASI INTERVAL ORAR.

       CAND INTALNIM DOI PROFESORI CARE INDEPLINESC ACESTE CRITERII INCEPEM SA VERIFICAM DETALII:

        1. DACA PRIMUL ARE <HARD>, AL DOILEA <SOFT> CA SI CONSTANGERI, IL VOM ALEGE PE <PRIMUL>

        2. DACA PRIMUL ARE <SOFT>, AL DOILEA <HARD> CA SI CONSTANGERI, IL VOM ALEGE PE <AL DOILEA>

        3. DACA <AMBII> AU <HARD> ATUNCI VA FI O <REZOLVARE MANUALA>, SETAM ID UL DE PE ACEA DORINTA PE NULL SI IL VOM <RAPORTA LA FRONT-END>

        4. DACA <AMBII> AU <SOFT> ATUNCI ATUNCI VOM LUA IN FUNCTIE DE INTEVAL(ORE DE CURS/LABORATOR CONSECUTIVE):

                a. DACA PRIMUL ARE INTERVAL SI AL DOILEA NU, IL LUAM PE PRIMUL,

                b. DACA E INVERS, IL LUAM PE CELALAT

                c. DACA AMBII  <NU AU INTERVAL>, IL PUNEM MOMENTAN PE PRIMUL, URMAND CA LA URMATOAREA ITERATIE SA FIE PROBABIL INLOCUIT DACA EXISTA CINEVA CU <HARD> CONSTRAINT

                d. DACA AMBII <AU INTERVAL>, VOM RAPORTA LA FRONT-END ACEASTA DECIZITE

        LA FIECARE "ALEGERE" VOM PUNE TOATE INFORMATIILE IN TABELA "IS_OCCUPIED_BY", DIN CARE SE VOR PUTEA EXTRAGE DATE ULTERIOR
        SI VOM "NOTA" TOTUL IN OBIECTUL "resolvedWih", FIIND RETURNAT FONT-END ULUI

     */

    @Autowired
    IsOccupiedByService isOccupiedByService;

    @Autowired
    WishService wishService;

    public List<Wish> resolveConstraints() {

        List<Wish> resolvedWish = new ArrayList<>();
        List<IsOccupiedBy> allDayList = isOccupiedByService.getAllDayList();

        while (resolvedWish.size() != wishService.getAllWishes().size()) {  // cat timp lista de Wish totala nu este egala cu lista de wish deja rezolvata repetam procesul
            allDayList.forEach(day -> {

                        List<Wish> dayWishList = new ArrayList<>();
                        dayWishList = wishService.getAllWishesByDayId(day.getDayId());
                        System.out.println("Day Wish List" + dayWishList);

                        List<Wish> finalDayWishList = dayWishList;
                        dayWishList.forEach(firstWish -> {

                            Wish secondWish = finalDayWishList.stream().filter(Wish -> firstWish.getClassroomId().equals(Wish.getClassroomId())).findAny().orElse(null); // aici facem un filtru cautand profesorii care ocupa aceiasi sala in ziua respectiva

                            if (firstWish.equals(secondWish)) {

                                if (!firstWish.getTeacherId().equals(secondWish.getTeacherId())) {

                                    if (firstWish.getConstraintHs().equals("hard") && secondWish.getConstraintHs().equals("soft")) { // daca primul are HARD atunci il setam pe el in lista

                                        allDayList.get((allDayList.indexOf(day))).setUserId(firstWish.getTeacherId()); // aici setam User Id-ul care are constrangerea HARD

                                        switch (firstWish.getStartCourseHour()) {  // aici setam intervalul
                                            case 8:
                                                allDayList.get((allDayList.indexOf(day))).setIsOccup8_10(Boolean.TRUE);
                                            case 10:
                                                allDayList.get((allDayList.indexOf(day))).setIsOccup10_12(Boolean.TRUE);
                                            case 12:
                                                allDayList.get((allDayList.indexOf(day))).setIsOccup12_14(Boolean.TRUE);
                                            case 14:
                                                allDayList.get((allDayList.indexOf(day))).setIsOccup14_16(Boolean.TRUE);
                                            case 16:
                                                allDayList.get((allDayList.indexOf(day))).setIsOccup16_18(Boolean.TRUE);
                                            case 18:
                                                allDayList.get((allDayList.indexOf(day))).setIsOccup18_20(Boolean.TRUE);
                                        }
                                        allDayList.get((allDayList.indexOf(day))).setClassroomId(firstWish.getClassroomId()); // aici setam sala

                                        resolvedWish.get(allDayList.indexOf(day)).setIsCompleted(Boolean.TRUE); // aici retam IsCompleted pe TRUE pentru a stii API de front ca acel Wish a fost rezolvat

                                    } else if (firstWish.getConstraintHs().equals("soft") && secondWish.getConstraintHs().equals("hard")) { // daca al doilea are HARD, atunci il setam pe el

                                        allDayList.get((allDayList.indexOf(day))).setUserId(secondWish.getTeacherId());

                                        switch (secondWish.getStartCourseHour()) {
                                            case 8:
                                                allDayList.get((allDayList.indexOf(day))).setIsOccup8_10(Boolean.TRUE);
                                            case 10:
                                                allDayList.get((allDayList.indexOf(day))).setIsOccup10_12(Boolean.TRUE);
                                            case 12:
                                                allDayList.get((allDayList.indexOf(day))).setIsOccup12_14(Boolean.TRUE);
                                            case 14:
                                                allDayList.get((allDayList.indexOf(day))).setIsOccup14_16(Boolean.TRUE);
                                            case 16:
                                                allDayList.get((allDayList.indexOf(day))).setIsOccup16_18(Boolean.TRUE);
                                            case 18:
                                                allDayList.get((allDayList.indexOf(day))).setIsOccup18_20(Boolean.TRUE);
                                        }
                                        allDayList.get((allDayList.indexOf(day))).setClassroomId(secondWish.getClassroomId());

                                        resolvedWish.get(allDayList.indexOf(day)).setIsCompleted(Boolean.TRUE);

                                    } else if (firstWish.getConstraintHs().equals(secondWish.getConstraintHs()) && firstWish.getConstraintHs().equals("hard")) {

                                        allDayList.get((allDayList.indexOf(day))).setUserId(null); //resolvare manuala, ambii au hard

                                    } else if (firstWish.getConstraintHs().equals(secondWish.getConstraintHs()) && firstWish.getConstraintHs().equals("soft")) {

                                        if (firstWish.getIsInterval().equals(Boolean.TRUE) && secondWish.getIsInterval().equals(Boolean.FALSE)) { // daca primul are mai multe ore consecutive, atunci se considera ca e interval, am considerat ca cel cu interval are prioritate, daca la ambii e soft
                                            allDayList.get((allDayList.indexOf(day))).setUserId(firstWish.getTeacherId());

                                            switch (firstWish.getStartCourseHour()) {
                                                case 8:
                                                    allDayList.get((allDayList.indexOf(day))).setIsOccup8_10(Boolean.TRUE);
                                                case 10:
                                                    allDayList.get((allDayList.indexOf(day))).setIsOccup10_12(Boolean.TRUE);
                                                case 12:
                                                    allDayList.get((allDayList.indexOf(day))).setIsOccup12_14(Boolean.TRUE);
                                                case 14:
                                                    allDayList.get((allDayList.indexOf(day))).setIsOccup14_16(Boolean.TRUE);
                                                case 16:
                                                    allDayList.get((allDayList.indexOf(day))).setIsOccup16_18(Boolean.TRUE);
                                                case 18:
                                                    allDayList.get((allDayList.indexOf(day))).setIsOccup18_20(Boolean.TRUE);
                                            }
                                            allDayList.get((allDayList.indexOf(day))).setClassroomId(firstWish.getClassroomId());

                                            resolvedWish.get(allDayList.indexOf(day)).setIsCompleted(Boolean.TRUE);

                                        } else if (firstWish.getIsInterval().equals(Boolean.FALSE) && secondWish.getIsInterval().equals(Boolean.TRUE)) {
                                            allDayList.get((allDayList.indexOf(day))).setUserId(secondWish.getTeacherId());

                                            switch (secondWish.getStartCourseHour()) {
                                                case 8:
                                                    allDayList.get((allDayList.indexOf(day))).setIsOccup8_10(Boolean.TRUE);
                                                case 10:
                                                    allDayList.get((allDayList.indexOf(day))).setIsOccup10_12(Boolean.TRUE);
                                                case 12:
                                                    allDayList.get((allDayList.indexOf(day))).setIsOccup12_14(Boolean.TRUE);
                                                case 14:
                                                    allDayList.get((allDayList.indexOf(day))).setIsOccup14_16(Boolean.TRUE);
                                                case 16:
                                                    allDayList.get((allDayList.indexOf(day))).setIsOccup16_18(Boolean.TRUE);
                                                case 18:
                                                    allDayList.get((allDayList.indexOf(day))).setIsOccup18_20(Boolean.TRUE);
                                            }
                                            allDayList.get((allDayList.indexOf(day))).setClassroomId(secondWish.getClassroomId());

                                            resolvedWish.get(allDayList.indexOf(day)).setIsCompleted(Boolean.TRUE);

                                        } else if (firstWish.getIsInterval().equals(secondWish.getIsInterval()) && firstWish.getConstraintHs().equals(Boolean.TRUE)) {

                                            allDayList.get((allDayList.indexOf(day))).setUserId(null); //resolvare manuala, ambii au soft, dar ambii au ore consecutive in aceiasi perioada

                                        } else if (firstWish.getIsInterval().equals(secondWish.getIsInterval()) && firstWish.getConstraintHs().equals(Boolean.FALSE)) {
                                            // daca ambii nu au interval si constraingere soft la amandoi atunci luam primul Wishes, cu posibilitatea de a fi "mutat" ulterior, daca pune un profesor constrangerea HARD acolo
                                            allDayList.get((allDayList.indexOf(day))).setUserId(firstWish.getTeacherId());

                                            switch (firstWish.getStartCourseHour()) {
                                                case 8:
                                                    allDayList.get((allDayList.indexOf(day))).setIsOccup8_10(Boolean.TRUE);
                                                case 10:
                                                    allDayList.get((allDayList.indexOf(day))).setIsOccup10_12(Boolean.TRUE);
                                                case 12:
                                                    allDayList.get((allDayList.indexOf(day))).setIsOccup12_14(Boolean.TRUE);
                                                case 14:
                                                    allDayList.get((allDayList.indexOf(day))).setIsOccup14_16(Boolean.TRUE);
                                                case 16:
                                                    allDayList.get((allDayList.indexOf(day))).setIsOccup16_18(Boolean.TRUE);
                                                case 18:
                                                    allDayList.get((allDayList.indexOf(day))).setIsOccup18_20(Boolean.TRUE);
                                            }
                                            allDayList.get((allDayList.indexOf(day))).setClassroomId(firstWish.getClassroomId());

                                            resolvedWish.get(allDayList.indexOf(day)).setIsCompleted(Boolean.TRUE);
                                        }
                                    }
                                }
                            }

                        });
                    }
            );

        }
        return resolvedWish;
    }
}
